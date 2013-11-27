package org.liujia.shop.action;


import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.liujia.shop.model.Product;
import org.liujia.shop.service.ProductService;


import com.opensymphony.xwork2.ActionContext;

public class ProductAction {
	
	private String keyword;
	private Product product;
	private int categoryId;
	private Integer productId;
	private ProductService productService;
	private File[] uploads;
    private String[] uploadFileNames;
    private String[] uploadContentTypes;
    
	
	public String list(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String requestSource = request.getParameter("requestSource");
		Map<String , Object> session = ActionContext.getContext().getSession();
		List<Product> list = productService.findAll();
		session.put("products",	 list);
		if("info".equals(requestSource)){
			return "INFO";
		}else if("manage".equals(requestSource)){
			return "MANAGE";
		}else{
			return null;
		}
	}
	
	public String show(){
		Map<String, Object> session=ActionContext.getContext().getSession();
		List<Product> products = productService.findByCategoryId(categoryId);
		session.put("products", products);
		return "SUCCESS";
	}
	
	public String detail(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String requestSource = request.getParameter("requestSource");
		Map<String, Object> session=ActionContext.getContext().getSession();
		product = productService.findById(productId);
		session.put("product", product);
		if("manage".equals(requestSource)){
			return "MANAGE";
		}else{
			return "SUCCESS";
		}
	}
	
	public String search(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		String temp = null;
		try {
			temp = new String(keyword.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		List<Product> list = productService.search(temp);
		if(list.size()!=0){
			session.put("products", list);
		}
		return "SUCCESS";
	}
	
	  // 文件上传
    @SuppressWarnings("deprecation")
    public String add() throws Exception {
    	if(product != null){
    		product.setAddTime(new Date());
    		productService.save(product);
    	}
    	product = productService.findProductByName(product.getName());
        // 获得upload路径的实际目录
    	String realPath = ServletActionContext.getRequest().getRealPath("/front/images/content/product");
        // 获得实际目录
        String targetDirectory = realPath;
        String[] mydir = new String[uploads.length];
        String[] tname = new String[uploads.length];
        for (int i = 0; i < uploads.length; i++) {
            // 生成保存文件的文件名称
            tname[i] = generateFileName(uploadFileNames[i],product.getId());
            // 保存文件的路径
            mydir[i] = targetDirectory + "\\" + product.getId()+"\\"+tname[i];
            // 建立一个目标文件
            File target = new File(targetDirectory+"\\" + product.getId(), tname[i]);
            // 将临时文件复制到目标文件
            FileUtils.copyFile(uploads[i], target);
        }
        return "SUCCESS";
    }

    private String generateFileName(String fileName,Integer productId) {
        fileName = fileName.replaceAll("\\d+", productId.toString());
        return fileName;
    }
    
	public static void main(String[] args) {
		String fileName = new ProductAction().generateFileName("404064/404064_ou_xs.jpg", 1);
		System.out.println(fileName);
	}
	 
	 public Integer getProductId() {
			return productId;
		}
		public void setProductId(Integer productId) {
			this.productId = productId;
		}
		public String getKeyword() {
			return keyword;
		}
		public void setKeyword(String keyword) {
			this.keyword = keyword;
		}
		public ProductService getProductService() {
			return productService;
		}
		public void setProductService(ProductService productService) {
			this.productService = productService;
		}
		public int getCategoryId() {
			return categoryId;
		}
		public void setCategoryId(int categoryId) {
			this.categoryId = categoryId;
		}

		public Product getProduct() {
			return product;
		}
		public void setProduct(Product product) {
			this.product = product;
		}

		public File[] getUpload() {
	        return this.uploads;
	    }

	    public void setUpload(File[] upload) {
	        this.uploads = upload;
	    }

	    public String[] getUploadFileName() {
	        return this.uploadFileNames;
	    }

	    public void setUploadFileName(String[] uploadFileName) {
	        this.uploadFileNames = uploadFileName;
	    }

	    public String[] getUploadContentType() {
	        return this.uploadContentTypes;
	    }

	    public void setUploadContentType(String[] uploadContentType) {
	        this.uploadContentTypes = uploadContentType;
	    }

}
