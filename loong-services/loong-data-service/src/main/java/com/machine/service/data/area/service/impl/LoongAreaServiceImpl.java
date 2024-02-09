package com.machine.service.data.area.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import com.machine.service.data.area.dao.ILoongAreaDao;
import com.machine.service.data.area.dao.dto.indto.LoongAreaInsertInDTO;
import com.machine.service.data.area.dao.dto.outdto.LoongAreaListOutDTO;
import com.machine.service.data.area.service.ILoongAreaService;
import com.machine.service.data.area.service.bo.LoongAreaReptileBO;
import com.machine.service.data.area.service.bo.inbo.LoongAreaInsertInBO;
import com.machine.service.data.area.service.bo.outbo.LoongAreaListOutBO;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class LoongAreaServiceImpl implements ILoongAreaService {

    private static final String ROOT_CODE = "000000000000";

    private static final String URL_PREFIX = "https://www.stats.gov.cn/sj/tjbz/tjyqhdmhcxhfdm/2023/";

    @Autowired
    private ILoongAreaDao areaDao;


    @Override
    public void init() {
        log.info("初始化区域信息");

        List<LoongAreaListOutDTO> areaProvinceList = areaDao.selectByParentCode(ROOT_CODE);
        if (CollectionUtil.isEmpty(areaProvinceList)) {
            processProvince();
        }

        for (LoongAreaListOutDTO areaProvince : areaProvinceList) {
            if (!areaProvince.getHasChild()) {
                continue;
            }
            List<LoongAreaListOutDTO> areaCityList = areaDao.selectByParentCode(areaProvince.getCode());
            if (CollectionUtil.isEmpty(areaCityList)) {
                processCity(areaProvince.getCode());
            }

            for (LoongAreaListOutDTO areaCity : areaCityList) {
                if (!areaCity.getHasChild()) {
                    continue;
                }
                List<LoongAreaListOutDTO> areaCountryList = areaDao.selectByParentCode(areaCity.getCode());

                //广东省-东完市、中山市（没有县级数据）
                //海南省-儋州市（没有县级数据）
                if ("441900000000".equals(areaCity.getCode()) ||
                        "442000000000".equals(areaCity.getCode()) ||
                        "460400000000".equals(areaCity.getCode())) {
                    if (CollectionUtil.isEmpty(areaCountryList)) {
                        processTown(areaCity.getCode(), true);
                    }

                    List<LoongAreaListOutDTO> areaTownList = areaDao.selectByParentCode(areaCity.getCode());
                    for (LoongAreaListOutDTO areaTown : areaTownList) {
                        if (!areaTown.getHasChild()) {
                            continue;
                        }

                        List<LoongAreaListOutDTO> areaVillageList = areaDao.selectByParentCode(areaTown.getCode());
                        if (CollectionUtil.isEmpty(areaVillageList)) {
                            processVillage(areaTown.getCode(), true);
                        }
                    }
                    continue;
                }
                if (CollectionUtil.isEmpty(areaCountryList)) {
                    processCountry(areaCity.getCode());
                }

                for (LoongAreaListOutDTO areaCountry : areaCountryList) {
                    if (!areaCountry.getHasChild()) {
                        continue;
                    }
                    List<LoongAreaListOutDTO> areaTownList = areaDao.selectByParentCode(areaCountry.getCode());
                    if (CollectionUtil.isEmpty(areaTownList)) {
                        processTown(areaCountry.getCode(), false);
                    }

                    for (LoongAreaListOutDTO areaTown : areaTownList) {
                        if (!areaTown.getHasChild()) {
                            continue;
                        }

                        List<LoongAreaListOutDTO> areaVillageList = areaDao.selectByParentCode(areaTown.getCode());
                        if (CollectionUtil.isEmpty(areaVillageList)) {
                            processVillage(areaTown.getCode(), false);
                        }
                    }
                }
            }
        }
    }

    @Override
    public Boolean insertBatch(List<LoongAreaInsertInBO> inBOList) {
        log.info("批量新增地区信息 inBOList:{}", JSONUtil.toJsonStr(inBOList));
        List<LoongAreaInsertInDTO> areaInsertInDTOList = JSONUtil.toList(JSONUtil.toJsonStr(inBOList), LoongAreaInsertInDTO.class);
        return areaDao.insertBatch(areaInsertInDTOList);
    }

    @Override
    public List<LoongAreaListOutBO> selectByLevel(Integer level) {
        List<LoongAreaListOutDTO> areaListOutDTOList = areaDao.selectByLevel(level);
        return JSONUtil.toList(JSONUtil.toJsonStr(areaListOutDTOList), LoongAreaListOutBO.class);
    }

    private void processProvince() {
        List<LoongAreaReptileBO> areaProvinceList = getProvince();
        List<LoongAreaInsertInDTO> insertInDTOS = JSONUtil.toList(JSONUtil.toJsonStr(areaProvinceList), LoongAreaInsertInDTO.class);
        for (LoongAreaInsertInDTO insertInDTO : insertInDTOS) {
            insertInDTO.setLevel(1);
        }
        areaDao.insertBatch(insertInDTOS);
        for (LoongAreaInsertInDTO insertInDTO : insertInDTOS) {
            processCity(insertInDTO.getCode());
        }
    }

    private void processCity(String provinceCode) {
        List<LoongAreaReptileBO> areaCityList = getCity(provinceCode);

        List<LoongAreaInsertInDTO> insertInDTOS = JSONUtil.toList(JSONUtil.toJsonStr(areaCityList), LoongAreaInsertInDTO.class);
        for (LoongAreaInsertInDTO insertInDTO : insertInDTOS) {
            insertInDTO.setLevel(2);
        }
        areaDao.insertBatch(insertInDTOS);
        for (LoongAreaInsertInDTO insertInDTO : insertInDTOS) {
            if (!insertInDTO.getHasChild()) {
                continue;
            }
            processCountry(insertInDTO.getCode());
        }
    }

    private void processCountry(String ctyCode) {
        //广东省-东完市、中山市（没有县级数据）
        //海南省-儋州市（没有县级数据）
        if ("441900000000".equals(ctyCode) ||
                "442000000000".equals(ctyCode) ||
                "460400000000".equals(ctyCode)) {
            List<LoongAreaReptileBO> areaTownList = getTown(ctyCode, true);

            List<LoongAreaInsertInDTO> insertInDTOS = JSONUtil.toList(JSONUtil.toJsonStr(areaTownList), LoongAreaInsertInDTO.class);
            for (LoongAreaInsertInDTO insertInDTO : insertInDTOS) {
                insertInDTO.setLevel(4);
            }
            areaDao.insertBatch(insertInDTOS);
            for (LoongAreaInsertInDTO insertInDTO : insertInDTOS) {
                if (!insertInDTO.getHasChild()) {
                    continue;
                }
                processVillage(insertInDTO.getCode(), true);
            }
            return;
        }

        List<LoongAreaReptileBO> areaCountryList = getCountry(ctyCode);

        List<LoongAreaInsertInDTO> insertInDTOS = JSONUtil.toList(JSONUtil.toJsonStr(areaCountryList), LoongAreaInsertInDTO.class);
        for (LoongAreaInsertInDTO insertInDTO : insertInDTOS) {
            insertInDTO.setLevel(3);
        }
        areaDao.insertBatch(insertInDTOS);
        for (LoongAreaInsertInDTO insertInDTO : insertInDTOS) {
            if (!insertInDTO.getHasChild()) {
                continue;
            }
            processTown(insertInDTO.getCode(), false);
        }
    }

    private void processTown(String countryCode, boolean shortUrl) {
        List<LoongAreaReptileBO> areaTownList = getTown(countryCode, shortUrl);

        List<LoongAreaInsertInDTO> insertInDTOS = JSONUtil.toList(JSONUtil.toJsonStr(areaTownList), LoongAreaInsertInDTO.class);
        for (LoongAreaInsertInDTO insertInDTO : insertInDTOS) {
            insertInDTO.setLevel(4);
        }
        areaDao.insertBatch(insertInDTOS);
        for (LoongAreaInsertInDTO insertInDTO : insertInDTOS) {
            if (!insertInDTO.getHasChild()) {
                continue;
            }
            processVillage(insertInDTO.getCode(), shortUrl);
        }
    }

    private void processVillage(String townCode, boolean shortUrl) {
        List<LoongAreaReptileBO> areaVillageList = getVillage(townCode, shortUrl);
        List<LoongAreaInsertInDTO> insertInDTOS = JSONUtil.toList(JSONUtil.toJsonStr(areaVillageList), LoongAreaInsertInDTO.class);
        for (LoongAreaInsertInDTO insertInDTO : insertInDTOS) {
            insertInDTO.setLevel(5);
        }
        areaDao.insertBatch(insertInDTOS);
    }

    @SneakyThrows
    private List<LoongAreaReptileBO> getProvince() {
        String url = URL_PREFIX + "index.html";
        log.info("爬虫省信息 url:{}", url);

        List<LoongAreaReptileBO> areaReptileBOList = new ArrayList<>();
        Document doc = Jsoup.connect(url).get();
        Elements provinceTableList = doc.getElementsByClass("provincetable");
        for (Element provinceTable : provinceTableList) {
            Elements provinceTrList = provinceTable.getElementsByClass("provincetr");
            for (Element provinceTr : provinceTrList) {
                Elements elementList = provinceTr.select("a[href]");
                for (Element element : elementList) {
                    String href = element.attr("href");
                    LoongAreaReptileBO entity = new LoongAreaReptileBO();
                    entity.setParentCode("000000000000");
                    entity.setCode(href.substring(0, 2) + "0000000000");
                    entity.setName(element.text());
                    entity.setHasChild(true);
                    areaReptileBOList.add(entity);
                }
            }
        }
        return areaReptileBOList;
    }

    @SneakyThrows
    private List<LoongAreaReptileBO> getCity(String provinceCode) {
        String url = URL_PREFIX + provinceCode.substring(0, 2) + ".html";
        log.info("爬虫市信息 provinceCode:{} url:{}", provinceCode, url);

        List<LoongAreaReptileBO> areaReptileBOList = new ArrayList<>();
        Elements cityTrList = Jsoup.connect(url).get().getElementsByClass("citytr");
        if (cityTrList.size() == 0) {
            log.error("爬虫市信息有误 provinceCode:{} url:{}", provinceCode, url);
            throw new RuntimeException("爬虫市信息有误");
        }
        for (Element cityTr : cityTrList) {
            Elements elementList = cityTr.select("a[href]");
            LoongAreaReptileBO entity = new LoongAreaReptileBO();
            if (elementList.size() <= 0) {
                elementList = cityTr.select("a");
                entity.setParentCode(provinceCode);
                entity.setCode(elementList.get(0).text());
                entity.setName(elementList.get(1).text());
                entity.setHasChild(false);
                areaReptileBOList.add(entity);
                continue;
            }
            Element cityCodeElement = elementList.get(0);
            Element cityNameElement = elementList.get(1);
            entity.setParentCode(provinceCode);
            entity.setCode(cityCodeElement.text());
            entity.setName(cityNameElement.text());
            entity.setHasChild(true);
            areaReptileBOList.add(entity);
        }
        return areaReptileBOList;
    }

    @SneakyThrows
    private List<LoongAreaReptileBO> getCountry(String ctyCode) {
        String url = URL_PREFIX + ctyCode.substring(0, 2) + "/" + ctyCode.substring(0, 4) + ".html";
        log.info("爬虫区信息 ctyCode:{} url:{}", ctyCode, url);

        List<LoongAreaReptileBO> areaReptileBOList = new ArrayList<>();
        Elements countryTrList = Jsoup.connect(url).get().getElementsByClass("countytr");
        if (countryTrList.size() == 0) {
            log.error("爬虫区信息有误 ctyCode:{} url:{}", ctyCode, url);
            throw new RuntimeException("爬虫区信息有误");
        }
        for (Element countryTr : countryTrList) {
            Elements elementList = countryTr.select("a[href]");
            LoongAreaReptileBO entity = new LoongAreaReptileBO();
            if (elementList.size() <= 0) {
                elementList = countryTr.select("td");
                entity.setParentCode(ctyCode);
                entity.setCode(elementList.get(0).text());
                entity.setName(elementList.get(1).text());
                entity.setHasChild(false);
                continue;
            }
            Element cityCodeElement = elementList.get(0);
            Element cityNameElement = elementList.get(1);
            entity.setParentCode(ctyCode);
            entity.setCode(cityCodeElement.text());
            entity.setName(cityNameElement.text());
            entity.setHasChild(true);
            areaReptileBOList.add(entity);
        }
        return areaReptileBOList;
    }


    @SneakyThrows
    private List<LoongAreaReptileBO> getTown(String countyCode, boolean shortUrl) {
        String url = URL_PREFIX + countyCode.substring(0, 2) + "/" +
                countyCode.substring(2, 4) + "/" +
                countyCode.substring(0, 6) + ".html";
        if (shortUrl) {
            url = URL_PREFIX + countyCode.substring(0, 2) + "/" + countyCode.substring(0, 4) + ".html";
        }
        log.info("爬虫镇信息 shortUrl:{} countyCode:{} url:{}", shortUrl, countyCode, url);

        List<LoongAreaReptileBO> areaReptileBOList = new ArrayList<>();
        Elements townTrList = Jsoup.connect(url).get().getElementsByClass("towntr");
        if (townTrList.size() == 0) {
            log.error("爬虫镇信息有误 shortUrl:{} ctyCode:{} url:{}", shortUrl, countyCode, url);
            throw new RuntimeException("爬虫镇信息有误");
        }
        for (Element townTr : townTrList) {
            Elements elementList = townTr.select("a[href]");
            LoongAreaReptileBO entity = new LoongAreaReptileBO();
            Element codeElement = elementList.get(0);
            Element nameElement = elementList.get(1);
            entity.setParentCode(countyCode);
            entity.setCode(codeElement.text());
            entity.setName(nameElement.text());
            entity.setHasChild(true);
            areaReptileBOList.add(entity);
        }
        return areaReptileBOList;
    }

    @SneakyThrows
    private List<LoongAreaReptileBO> getVillage(String townCode, boolean shortUrl) {
        String url = URL_PREFIX + townCode.substring(0, 2) + "/" +
                townCode.substring(2, 4) + "/" +
                townCode.substring(4, 6) + "/" +
                townCode.substring(0, 9) + ".html";
        if (shortUrl) {
            url = URL_PREFIX + townCode.substring(0, 2) + "/" +
                    townCode.substring(2, 4) + "/" +
                    townCode.substring(0, 9) + ".html";
        }
        log.info("爬虫村信息 shortUrl:{} townCode:{} url:{}", shortUrl, townCode, url);

        List<LoongAreaReptileBO> areaReptileBOList = new ArrayList<>();
        Elements villageTrList = Jsoup.connect(url).get().getElementsByClass("villagetr");
        if (villageTrList.size() == 0) {
            log.error("爬虫村信息有误 shortUrl:{} shortUrl:{} ctyCode:{} url:{}", shortUrl, shortUrl, townCode, url);
            throw new RuntimeException("爬虫村信息有误");
        }
        for (Element villageTr : villageTrList) {
            Elements elementList = villageTr.select("td");
            LoongAreaReptileBO entity = new LoongAreaReptileBO();
            Element codeElement = elementList.get(0);
            Element nameElement = elementList.get(2);
            entity.setParentCode(townCode);
            entity.setCode(codeElement.text());
            entity.setCategoryCode(elementList.get(1).text());
            entity.setName(nameElement.text());
            entity.setHasChild(false);
            areaReptileBOList.add(entity);
        }
        return areaReptileBOList;
    }

}
