package com.rental.action;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.rental.bean.TblRoom;
import com.rental.bean.TblRoomImage;
import com.rental.bean.TblUser;
import com.rental.service.RoomImgService;
import com.rental.service.RoomService;
import com.rental.service.impl.UserServiceImpl;
import com.rental.util.DateUtil;
@SuppressWarnings("serial")
@ParentPackage("base")
@Results  
({  
	@Result(name="json",type="json",params={"root","root"}),
	@Result(name="page",type="json",params={"root","pageBean"}),
	@Result(name="quit",type="redirect",location="/login.jsp")   
  
})
public class FileAction extends ActionSupport {
	private File upload;//上传文件
	private String uploadFileName;//上传文件名称
	private String uploadType;//上传文件类型
	private String savePath="/upload/";
	
	@Resource(name="roomService")
	private RoomService roomService;
	@Resource(name="imgService")
	private RoomImgService imgService;
	@Resource(name="userService")
	private UserServiceImpl service;
	
	private int userId;
	private int roomId;
	private int imgId;
	
	private String inputPath;
	private Map<String, Object> root;
	/**
	 * 上传头像
	 * @return
	 */
	public String uploadHeadImg(){
		savePath+="head/";
		try{
			if(uploadFile()==200){
				TblUser user = service.findById(userId);
				user.setHeadImg(inputPath);
				service.updateUser(user);
			}
		}catch (Exception e) {
			e.printStackTrace();
			root.put("resultCode", 500);
			root.put("msg", "系统异常！");
		}
		
		return "json";
	}
	/**
	 * 上传楼盘文件
	 * @return
	 */
	public String uploadRoomImg(){
		savePath+="room/";
		try{
			if(uploadFile()==200){
				TblRoom room = roomService.findById(roomId);
				TblUser user =(TblUser)ActionContext.getContext().getSession().get("rental_user");
				String time= DateUtil.toYMD(new Date().getTime());
				room.setUpdate(user);
				room.setUpdateDate(time);
				TblRoomImage img = new TblRoomImage();
				img.setCreate(user);
				img.setCreateDate(time);
				img.setImgPath(inputPath);
				img.setImgStyle(uploadType);//in out
				img.setRoom(room);
				room.getImgSet().add(img);
				roomService.updateRoom(room);
			}
		
		}catch (Exception e) {
			e.printStackTrace();
			root.put("resultCode", 500);
			root.put("msg", "系统异常！");
		}
		return "json";
	}

	/**
	 * 删除文件
	 * @return
	 */
	public String deleteFile(){
		root= new HashMap<String, Object>();
		String realPath = ServletActionContext.getServletContext().getRealPath(inputPath.replace("/Rental/", ""));
		try{
			File file = new File(realPath);
			if(file.exists()){
				if(inputPath.indexOf("room")!=-1){
					imgService.deleteRoomImg(inputPath.replace("/Rental/upload/room/", ""));
				}else{
					TblUser user=service.findById(userId);
					user.setHeadImg("");
					service.updateUser(user);
				}
				file.delete();
				root.put("resultCode", 200);
				root.put("msg", "删除成功！");
			}else{
				root.put("resultCode", 201);
				root.put("msg", "文件不存在！");
			}
		}catch (Exception e) {
			e.printStackTrace();
			root.put("resultCode", 500);
			root.put("msg", "系统异常,删除失败！");
		}
		return "json";
	}
	
	/**
	 * 上传文件
	 */
	private int uploadFile(){
		String realPath = ServletActionContext.getServletContext().getRealPath(savePath);
		TblUser user = (TblUser)ActionContext.getContext().getSession().get("rental_user");
		root= new HashMap<String, Object>();
		try{
			if(upload!=null){
				String name=new Date().getTime()+"_"+user.getUserName()+"_"+uploadFileName;
				String dstPath = realPath +"\\"+name ;
				inputPath = name;
				File dst = new File(dstPath);
				FileUtils.copyFile(this.upload, dst);
				root.put("resultCode", 200);
				root.put("msg", "上传成功！");
				root.put("data", name);
				return 200;
			}
			return 201;
		}catch (Exception e) {
			root.put("resultCode", 500);
			root.put("msg", "系统异常！");
			return 500;
		}
	}
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getUploadType() {
		return uploadType;
	}
	public void setUploadType(String uploadType) {
		this.uploadType = uploadType;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public String getInputPath() {
		return inputPath;
	}
	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}
	public Map<String, Object> getRoot() {
		return root;
	}
	public void setRoot(Map<String, Object> root) {
		this.root = root;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public int getImgId() {
		return imgId;
	}
	public void setImgId(int imgId) {
		this.imgId = imgId;
	}
	
}
