package com.machine.service.iam.area.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import com.machine.service.iam.area.service.bo.DragonAreaReptileBO;
import com.machine.service.iam.area.dao.IDragonAreaDao;
import com.machine.service.iam.area.dao.dto.indto.DragonAreaInsertInDTO;
import com.machine.service.iam.area.dao.dto.outdto.DragonAreaListOutDTO;
import com.machine.service.iam.area.service.IDragonAreaService;
import com.machine.service.iam.area.service.bo.inbo.DragonAreaInsertInBO;
import com.machine.service.iam.area.service.bo.outbo.DragonAreaListOutBO;
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
public class DragonAreaServiceImpl implements IDragonAreaService {

    private static final String ROOT_CODE = "000000000000";

    private static final String URL_PREFIX = "https://www.stats.gov.cn/sj/tjbz/tjyqhdmhcxhfdm/2023/";

    @Autowired
    private IDragonAreaDao dragonAreaDao;


    @Override
    public void init() {
        log.info("初始化区域信息");

        List<DragonAreaListOutDTO> areaProvinceList = dragonAreaDao.selectByParentCode(ROOT_CODE);
        if (CollectionUtil.isEmpty(areaProvinceList)) {
            processProvince();
        }

        for (DragonAreaListOutDTO areaProvince : areaProvinceList) {
            if (!areaProvince.getHasChild()) {
                continue;
            }
            List<DragonAreaListOutDTO> areaCityList = dragonAreaDao.selectByParentCode(areaProvince.getCode());
            if (CollectionUtil.isEmpty(areaCityList)) {
                processCity(areaProvince.getCode());
            }

            for (DragonAreaListOutDTO areaCity : areaCityList) {
                if (!areaCity.getHasChild()) {
                    continue;
                }
                List<DragonAreaListOutDTO> areaCountryList = dragonAreaDao.selectByParentCode(areaCity.getCode());

                //广东省-东完市、中山市（没有县级数据）
                //海南省-儋州市（没有县级数据）
                if ("441900000000".equals(areaCity.getCode()) ||
                        "442000000000".equals(areaCity.getCode()) ||
                        "460400000000".equals(areaCity.getCode())) {
                    if (CollectionUtil.isEmpty(areaCountryList)) {
                        processTown(areaCity.getCode(), true);
                    }

                    List<DragonAreaListOutDTO> areaTownList = dragonAreaDao.selectByParentCode(areaCity.getCode());
                    for (DragonAreaListOutDTO areaTown : areaTownList) {
                        if (!areaTown.getHasChild()) {
                            continue;
                        }

                        List<DragonAreaListOutDTO> areaVillageList = dragonAreaDao.selectByParentCode(areaTown.getCode());
                        if (CollectionUtil.isEmpty(areaVillageList)) {
                            processVillage(areaTown.getCode(), true);
                        }
                    }
                    continue;
                }
                if (CollectionUtil.isEmpty(areaCountryList)) {
                    processCountry(areaCity.getCode());
                }

                for (DragonAreaListOutDTO areaCountry : areaCountryList) {
                    if (!areaCountry.getHasChild()) {
                        continue;
                    }
                    List<DragonAreaListOutDTO> areaTownList = dragonAreaDao.selectByParentCode(areaCountry.getCode());
                    if (CollectionUtil.isEmpty(areaTownList)) {
                        processTown(areaCountry.getCode(), false);
                    }

                    for (DragonAreaListOutDTO areaTown : areaTownList) {
                        if (!areaTown.getHasChild()) {
                            continue;
                        }

                        List<DragonAreaListOutDTO> areaVillageList = dragonAreaDao.selectByParentCode(areaTown.getCode());
                        if (CollectionUtil.isEmpty(areaVillageList)) {
                            processVillage(areaTown.getCode(), false);
                        }
                    }
                }
            }
        }
    }

    @Override
    public Boolean insertBatch(List<DragonAreaInsertInBO> inBOList) {
        log.info("批量新增地区信息 inBOList:{}", JSONUtil.toJsonStr(inBOList));
        List<DragonAreaInsertInDTO> areaInsertInDTOList = JSONUtil.toList(JSONUtil.toJsonStr(inBOList), DragonAreaInsertInDTO.class);
        return dragonAreaDao.insertBatch(areaInsertInDTOList);
    }

    @Override
    public List<DragonAreaListOutBO> selectByLevel(Integer level) {
        List<DragonAreaListOutDTO> areaListOutDTOList = dragonAreaDao.selectByLevel(level);
        return JSONUtil.toList(JSONUtil.toJsonStr(areaListOutDTOList), DragonAreaListOutBO.class);
    }

    private void processProvince() {
        List<DragonAreaReptileBO> areaProvinceList = getProvince();
        List<DragonAreaInsertInDTO> insertInDTOS = JSONUtil.toList(JSONUtil.toJsonStr(areaProvinceList), DragonAreaInsertInDTO.class);
        for (DragonAreaInsertInDTO insertInDTO : insertInDTOS) {
            insertInDTO.setLevel(1);
        }
        dragonAreaDao.insertBatch(insertInDTOS);
        for (DragonAreaInsertInDTO insertInDTO : insertInDTOS) {
            processCity(insertInDTO.getCode());
        }
    }

    private void processCity(String provinceCode) {
        List<DragonAreaReptileBO> areaCityList = getCity(provinceCode);

        List<DragonAreaInsertInDTO> insertInDTOS = JSONUtil.toList(JSONUtil.toJsonStr(areaCityList), DragonAreaInsertInDTO.class);
        for (DragonAreaInsertInDTO insertInDTO : insertInDTOS) {
            insertInDTO.setLevel(2);
        }
        dragonAreaDao.insertBatch(insertInDTOS);
        for (DragonAreaInsertInDTO insertInDTO : insertInDTOS) {
            processCountry(insertInDTO.getCode());
        }
    }

    private void processCountry(String ctyCode) {
        //广东省-东完市、中山市（没有县级数据）
        //海南省-儋州市（没有县级数据）
        if ("441900000000".equals(ctyCode) ||
                "442000000000".equals(ctyCode) ||
                "460400000000".equals(ctyCode)) {
            List<DragonAreaReptileBO> areaTownList = getTown(ctyCode, true);

            List<DragonAreaInsertInDTO> insertInDTOS = JSONUtil.toList(JSONUtil.toJsonStr(areaTownList), DragonAreaInsertInDTO.class);
            for (DragonAreaInsertInDTO insertInDTO : insertInDTOS) {
                insertInDTO.setLevel(4);
            }
            dragonAreaDao.insertBatch(insertInDTOS);
            for (DragonAreaInsertInDTO insertInDTO : insertInDTOS) {
                processVillage(insertInDTO.getCode(), true);
            }
            return;
        }

        List<DragonAreaReptileBO> areaCountryList = getCountry(ctyCode);

        List<DragonAreaInsertInDTO> insertInDTOS = JSONUtil.toList(JSONUtil.toJsonStr(areaCountryList), DragonAreaInsertInDTO.class);
        for (DragonAreaInsertInDTO insertInDTO : insertInDTOS) {
            insertInDTO.setLevel(3);
        }
        dragonAreaDao.insertBatch(insertInDTOS);
        for (DragonAreaInsertInDTO insertInDTO : insertInDTOS) {
            processTown(insertInDTO.getCode(), false);
        }
    }

    private void processTown(String countryCode, boolean shortUrl) {
        List<DragonAreaReptileBO> areaTownList = getTown(countryCode, shortUrl);

        List<DragonAreaInsertInDTO> insertInDTOS = JSONUtil.toList(JSONUtil.toJsonStr(areaTownList), DragonAreaInsertInDTO.class);
        for (DragonAreaInsertInDTO insertInDTO : insertInDTOS) {
            insertInDTO.setLevel(4);
        }
        dragonAreaDao.insertBatch(insertInDTOS);
        for (DragonAreaInsertInDTO insertInDTO : insertInDTOS) {
            processVillage(insertInDTO.getCode(), shortUrl);
        }
    }

    private void processVillage(String townCode, boolean shortUrl) {
        List<DragonAreaReptileBO> areaVillageList = getVillage(townCode, shortUrl);
        List<DragonAreaInsertInDTO> insertInDTOS = JSONUtil.toList(JSONUtil.toJsonStr(areaVillageList), DragonAreaInsertInDTO.class);
        for (DragonAreaInsertInDTO insertInDTO : insertInDTOS) {
            insertInDTO.setLevel(5);
        }
        dragonAreaDao.insertBatch(insertInDTOS);
    }

    @SneakyThrows
    private List<DragonAreaReptileBO> getProvince() {
        String url = URL_PREFIX + "index.html";
        log.info("爬虫省信息 url:{}", url);

        List<DragonAreaReptileBO> areaReptileBOList = new ArrayList<>();
        Document doc = Jsoup.connect(url).get();
        Elements provinceTableList = doc.getElementsByClass("provincetable");
        for (Element provinceTable : provinceTableList) {
            Elements provinceTrList = provinceTable.getElementsByClass("provincetr");
            for (Element provinceTr : provinceTrList) {
                Elements elementList = provinceTr.select("a[href]");
                for (Element element : elementList) {
                    String href = element.attr("href");
                    DragonAreaReptileBO entity = new DragonAreaReptileBO();
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
    private List<DragonAreaReptileBO> getCity(String provinceCode) {
        String url = URL_PREFIX + provinceCode.substring(0, 2) + ".html";
        log.info("爬虫市信息 provinceCode:{} url:{}", provinceCode, url);

        List<DragonAreaReptileBO> areaReptileBOList = new ArrayList<>();
        Elements cityTrList = Jsoup.connect(url).get().getElementsByClass("citytr");
        if (cityTrList.size() == 0) {
            log.error("爬虫市信息有误 provinceCode:{} url:{}", provinceCode, url);
            throw new RuntimeException("爬虫市信息有误");
        }
        for (Element cityTr : cityTrList) {
            Elements elementList = cityTr.select("a[href]");
            DragonAreaReptileBO entity = new DragonAreaReptileBO();
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
    private List<DragonAreaReptileBO> getCountry(String ctyCode) {
        String url = URL_PREFIX + ctyCode.substring(0, 2) + "/" + ctyCode.substring(0, 4) + ".html";
        log.info("爬虫区信息 ctyCode:{} url:{}", ctyCode, url);

        List<DragonAreaReptileBO> areaReptileBOList = new ArrayList<>();
        Elements countryTrList = Jsoup.connect(url).get().getElementsByClass("countytr");
        if (countryTrList.size() == 0) {
            log.error("爬虫区信息有误 ctyCode:{} url:{}", ctyCode, url);
            throw new RuntimeException("爬虫区信息有误");
        }
        for (Element countryTr : countryTrList) {
            Elements elementList = countryTr.select("a[href]");
            DragonAreaReptileBO entity = new DragonAreaReptileBO();
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
    private List<DragonAreaReptileBO> getTown(String countyCode, boolean shortUrl) {
        String url = URL_PREFIX + countyCode.substring(0, 2) + "/" +
                countyCode.substring(2, 4) + "/" +
                countyCode.substring(0, 6) + ".html";
        if (shortUrl) {
            url = URL_PREFIX + countyCode.substring(0, 2) + "/" + countyCode.substring(0, 4) + ".html";
        }
        log.info("爬虫镇信息 shortUrl:{} countyCode:{} url:{}", shortUrl, countyCode, url);

        List<DragonAreaReptileBO> areaReptileBOList = new ArrayList<>();
        Elements townTrList = Jsoup.connect(url).get().getElementsByClass("towntr");
        if (townTrList.size() == 0) {
            log.error("爬虫镇信息有误 shortUrl:{} ctyCode:{} url:{}", shortUrl, countyCode, url);
            throw new RuntimeException("爬虫镇信息有误");
        }
        for (Element townTr : townTrList) {
            Elements elementList = townTr.select("a[href]");
            DragonAreaReptileBO entity = new DragonAreaReptileBO();
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
    private List<DragonAreaReptileBO> getVillage(String townCode, boolean shortUrl) {
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

        List<DragonAreaReptileBO> areaReptileBOList = new ArrayList<>();
        Elements villageTrList = Jsoup.connect(url).get().getElementsByClass("villagetr");
        if (villageTrList.size() == 0) {
            log.error("爬虫村信息有误 shortUrl:{} shortUrl:{} ctyCode:{} url:{}", shortUrl, shortUrl, townCode, url);
            throw new RuntimeException("爬虫村信息有误");
        }
        for (Element villageTr : villageTrList) {
            Elements elementList = villageTr.select("td");
            DragonAreaReptileBO entity = new DragonAreaReptileBO();
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
