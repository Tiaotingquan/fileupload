package com.ttq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Controller
@ResponseBody
@MultipartConfig
public class Ddemo {

    @RequestMapping("/test")
    public String Test011(){
        return "这是大傻逼";
    }

    @RequestMapping("/up/{tt}")
    public String  show(@PathVariable("tt") String tt){

        System.out.println(tt+"===============");
        return "傻逼二号";
    }
//    @RequestMapping("/upload")
//    public Integer uploadFile(@RequestParam(value="file",required=false) MultipartFile file, HttpServletRequest request,
//            HttpServletResponse response){
//        Part part = null;
//        try {
//            part = request.getPart("file");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ServletException e) {
//            e.printStackTrace();
//        }
//        System.out.println(part);
//        //判断是否有文件上传
//        if(part!=null && part.getSize()>0) {
//            //在files目录中创建子目录，格式：/files/yyyy/mm/dd
//            Date now = new Date();
//            Calendar cal = Calendar.getInstance();
//            int year = cal.get(Calendar.YEAR);
//            int month = cal.get(Calendar.MONTH) + 1;
//            int day = cal.get(Calendar.DAY_OF_MONTH);
//            //构建文件保存的url地址
//            String path = "/files/" + year + "/" + month + "/" + day;
//            //获取文件名称--目的：获取文件的后缀名
//            String filename = part.getSubmittedFileName();
////            获取名字
//
//            System.out.println("名称：" + filename);
//
//            //获取文件的后缀名
//            String extname = filename.substring(filename.lastIndexOf("."));
//            //System.out.println(extname);
//            //构建完整的文件保存url地址；文件名称采用uuid
//            //此路径就是保存到数据库中的路径
//            String filepath = path + "/" + UUID.randomUUID().toString() + extname;
//            //获取文件的物理路径
//            String realpath = this.getServletContext().getRealPath(filepath);
//            //创建目录
//            String dis = this.getServletContext().getRealPath(path);
//            File f = new File(dis);
//            if (!f.exists() && !f.isDirectory()) {
//                f.mkdirs();
//            }
//            //保存文件
//            try {
//                part.write(realpath);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return 1;
//    }


}
