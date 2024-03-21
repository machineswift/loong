package com.machine.test.temp.qiyu;

import cn.hutool.json.JSONUtil;
import com.dtyunxi.thirdparty.qiyu.session.QiYuStaffClient;
import com.dtyunxi.thirdparty.qiyu.session.model.CommonResult;
import com.dtyunxi.thirdparty.qiyu.session.model.request.QiYuStaffListRequest;
import com.dtyunxi.thirdparty.qiyu.session.model.response.QiYuStaffListResponse;
import com.dtyunxi.thirdparty.qiyu.session.util.FileUtil;
import com.dtyunxi.thirdparty.qiyu.session.util.MediaUtil;
import com.machine.test.temp.qiyu.model.QueryQueueResult;
import com.machine.test.temp.qiyu.model.eo.ComplaintEo;
import com.machine.test.temp.qiyu.model.eo.TicketEo;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author Qian.YongYan
 * @create 2024-03-13 16:22
 */
@Slf4j
public class QiyuTest {

    private static final String appKey = "89ddbe0d5eaca34cd6132fc54ffe66fc";
    private static final String foreignId = "aa#";
    private static final String appSecret = "42BE31FAC1AE403CB4566ABC719913E8";

    private SessionClient client = new SessionClient(appKey, appSecret);

    private static String path = "https://dev-test-obs.obs.cn-east-3.myhuaweicloud.com/xijie2pfrx6xTnC.jpg";

    private static String str = "https://ysf.nosdn.127.net/9592a34ac645e3e71fc8994eeecb7dac?Signature=SWqH9aqHU%2BlVGInetaENuCqscmazLSazOlnQkmDK7Fs%3D&Expires=2656402648&NOSAccessKeyId=b7e7f6715c6a4cffab9d28b5404f6f52";

    public static void main(String[] args) throws Exception {
        QiyuTest test = new QiyuTest();
        //test.queryStaffGroup();
        test.testCreateTicket();
        //test.sendImageMessage(foreignId, path);
        //test.queryTicketDetail("101000420");

       // test.queryStaffList();
    }


    public void queryStaffList() {
        QiYuStaffClient client = new QiYuStaffClient(appKey, appSecret);
        QiYuStaffListRequest request = new QiYuStaffListRequest();
        //request.setStatus(1);
        request.setRole(0);
        List<QiYuStaffListResponse> responseList = client.staffList(request).getData();
        log.info("七鱼客服列表:{}", JSONUtil.toJsonStr(responseList));
    }

    public void queryStaffGroup() throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("staff", "false");
        map.put("emptyGroup", "false");
        QueryQueueResult rs = client.queryStaffGroup(map);
        System.out.println("rs = " + rs);
    }

    public void queryTicketDetail(String ticketId) throws IOException {
        QueryQueueResult rs = client.queryTicketDetail(ticketId);
        System.out.println("rs = " + rs);
    }

    public void testCreateTicket() throws IOException {
        TicketEo eo = new TicketEo();
        eo.setTitle("扫呗投诉");
        eo.setUid("1593630763");
        eo.setUserMobile("1593630763");
        //eo.setStaffId("6454385");
        eo.setStaffId("6461160");
        ComplaintEo complaintEo = new ComplaintEo();
        complaintEo.setMerchantNumber("商户号");
        complaintEo.setMerchantName("商户名称");
        complaintEo.setTransactionOrderNumber("交易订单号");
        complaintEo.setComplaintNumber("投诉单号");
        complaintEo.setComplainantContact("1593630763");
        complaintEo.setComplaintContent("孬吃");
        complaintEo.setComplaintDate(LocalDateTime.now().toString());
        complaintEo.setComplaintSource("微信");
        complaintEo.setComplaintStatus("待处理");
        eo.setContent(complaintEo.buildContent());
        //eo.setStaffId("6461160");
        eo.setTargetGroupId(484446361);
        //eo.setGroupId(484446361);
        List<TicketEo.Attachment> list = new ArrayList<>();
        list.add(buildAttachment("这是文件.jpg", path));
        list.add(buildAttachment("还是文件.jpg", path));
        eo.setAttachments(list);
        QueryQueueResult ticket = client.createTicket(eo);
        System.out.println("ticket = " + ticket);
    }

    private TicketEo.Attachment buildAttachment(String fileName, String url) throws IOException {
        TicketEo.Attachment attachment = new TicketEo.Attachment();
        attachment.setFileName(fileName);
        attachment.setType(1);

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        InputStream is = connection.getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        byte[] bytes = bos.toByteArray();
        is.close();
        connection.disconnect();

        attachment.setPayload(Base64.getEncoder().encodeToString(bytes));
        return attachment;
    }

    private CommonResult sendImageMessage(String fromUid, String path) throws Exception {
        File tempFile = null;
        CommonResult commonResult = null;
        try {
            URLConnection connection = new URL(path).openConnection();
            InputStream is = connection.getInputStream();

            int dotIndex = path.lastIndexOf('.');
            String suffix = dotIndex > 0 && dotIndex < path.length() - 1 ? path.substring(dotIndex) : ".jpg";

            // 创建临时文件
            tempFile = File.createTempFile("qiYuTempFilePrefix", suffix);
            // 创建文件输出流，将下载的文件内容写入临时文件
            FileOutputStream os = new FileOutputStream(tempFile);

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            is.close();

            String tempFilePath = tempFile.getPath();
            String md5 = FileUtil.getMd5(tempFilePath);
            long size = FileUtil.getSize(tempFilePath);
            String url = client.uploadFile(tempFilePath, md5);

            int[] imageSize = MediaUtil.querySize(tempFilePath);
            int width = imageSize[0];
            int height = imageSize[1];

            commonResult = client.sendImageMessage(fromUid, url, md5, size, width, height);
        } finally {
            if (tempFile != null) {
                tempFile.delete();
            }
        }
        return commonResult;
    }
}
