package com.ttq.controller;


import com.fasterxml.jackson.databind.util.JSONPObject;
import com.ttq.comon.FastDFSClient;

import com.ttq.pojo.File;
import com.ttq.services.service.FileService;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * 文件上传的控制层
 */
@Controller
@RequestMapping("/file")
//响应体，返回数据
@ResponseBody
@CrossOrigin
public class FileUploadController {
    // 建立获取图片实体
    private File file = new File();

    @Autowired
    private FileService fileService;
    @RequestMapping("/test")
    public String test(){
        return "爱你一万年！";
    }


    /**
     * 文件上传接口
     * @return
     */
    //使用Post方法
    @PostMapping("/upload")

    // @ApiImplicitParam(name = "file",value = "上传文件")
    //对参数的要求，必须有或者不必须有
    public String upload(@RequestParam(value="file",required=false)MultipartFile file, HttpServletRequest request){
        String filename= "";
        String uuid = "";
        String message = "";
        //保存上传
        try{
            if(file!=null){
                String originalName = file.getOriginalFilename();
                // 获取文件后缀名
                String prefix=originalName.substring(originalName.lastIndexOf(".")+1);
                System.out.println("后缀："+prefix);

                Part part = request.getPart("file");
                System.out.println("文件名称："+part);
                //判断是否有文件上传
                if(part!=null && part.getSize()>0) {
                    //在files目录中创建子目录，格式：/files/yyyy/mm/dd
                    Date now = new Date();
                    Calendar cal = Calendar.getInstance();
                    int year = cal.get( Calendar.YEAR );
                    int month = cal.get( Calendar.MONTH ) + 1;
                    int day = cal.get( Calendar.DAY_OF_MONTH );
                    //构建文件保存的url地址
                    String path = "/files/" + year + "/" + month + "/" + day;

                    System.out.println("保存的URL地址："+path);

                    // System.out.println(extname);
                    // 构建完整的文件保存url地址；文件名称采用uuid
                    // 路径就是保存到数据库中的路径
                    uuid = UUID.randomUUID().toString();
                    // 保存的路径加名称
                    String filepath = path + "/" + uuid +"."+ prefix;
                    filename = filepath;
                    // 获取文件的物理路径
                    String realpath = request.getRealPath(filepath);
                    // 创建目录
                    String dis = request.getRealPath( path );
                    java.io.File f = new java.io.File(dis);
                    if (!f.exists() && !f.isDirectory()) {
                        f.mkdirs();
                    }
                    // 保存文件
                    part.write( realpath );
                    //                // 建立工具类对象
//                FastDFSClient dfs = new FastDFSClient();
//                // filename返回值，表示存储的路径，file.getBytes()是内容，prefix是文件格式
//                filename = dfs.uploadFile(file.getBytes(),prefix);
                    this.file.setFileid(uuid);
                    this.file.setCreatetime(new Date());
                    this.file.setFileaddress(filename);
                    this.file.setOldfilename(originalName);
                    this.file.setFilesize(String.valueOf(file.getSize()));
                    this.file.setFiletype(prefix);
                    this.file.setFilenmae(uuid+"."+prefix);
                    System.out.println(filename);
                    // 向数据库中添加文件信息
                    Integer rtn = fileService.insert(this.file);
                    String result = uuid;
                    if(rtn>0){
                        message =  result;
                    }
                    else{
                        message = "413";
                    }
                }
                else {
                    message = "请上传文件！";
                }
            }
            else{
                // 无文件上传
                message = "请上传文件！";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            return message;
        }

    }
    @GetMapping("/download")
    public String getFile(HttpServletRequest request,HttpServletResponse resp){
        // 如果已经上传过文件，那么就能够进行下载，否则返回字符串“状态码：410”
        if(file.getFileaddress()==null ||file.getFileaddress().equals("")){
            return "状态码：410";
        }
        else{
            // 1.获取下载文件的路径
            String namePath = file.getFileaddress().replace("/", "\\"+"\\");
            System.out.println("namepath:"+namePath);
            String filePath = "http://localhost:8080"+namePath;
//        http://localhost:8080/file/upload
//        String realPath = "F:\\学习\\11学习\\强化练习\\复习\\重点.txt";
//        System.out.println("下载文件的路径："+realPath);
            // 2.下载的文件名是啥
            String filename = filePath.substring(filePath.lastIndexOf("\\") + 1);
            System.out.println("文件名："+filename);
            // 3.设置想办法让浏览器能够支持（Content-Disposition)下载我们需要的东西
            try {
                filename= URLEncoder.encode(filename, "UTF-8");//设置编码方式
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            System.out.println("filepath:"+filePath);
            resp.setHeader("Content-Disposition","attachment;filename="+filename);
            // 4.获取下载文件的输入流
            try {
                FileInputStream in = new FileInputStream(request.getRealPath(file.getFileaddress()));
                // 5.创建缓冲区
                int len = 0;
                byte[] buffer = new byte[1024];
                // 6.获取OutputStream对象
                ServletOutputStream out = resp.getOutputStream();
                // 7.将FileoutputStream流写入到buffer缓冲区
                while ((len = in.read(buffer))>0) {
                    // 8.使用OutputStream将缓冲区中的数据输出到客户端
                    out.write(buffer,0,len);
                }
                //关闭流
                in.close();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return filePath;
        }

    }
    @GetMapping("/returnmsg")
    public File returnJsonMsg(ModelAndView modelAndView){
        if(this.file==null || this.file.getFileid()==null){
            modelAndView.addObject("msg","请上传文件");
            return this.file;
        }
//        方式一：在后台对数据进行json格式化，1、引入依赖包即可
//        JSONObject jsonpObject = new JSONObject();
//        方式二直接返回对象或数组给前端，让前端进行数据格式化转为json
        else{
            return this.file;
        }
    }

}
