package org.liujia.shop.action;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


import org.apache.struts2.ServletActionContext;
import org.liujia.core.service.QueryService;
import org.liujia.shop.model.Category;
import org.liujia.shop.model.Product;
import org.liujia.shop.service.CategoryService;
import org.liujia.shop.service.ProductService;


import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("unchecked")
public class ProductAction {
	private static final long serialVersionUID = 572146812454l ;
	private static final int BUFFER_SIZE = 16 * 1024 ;
	
	private String keyword;
	private Product product;
	private int categoryId;
	private Integer productId;
	private ProductService productService;
	private CategoryService categoryService;
	private List<File> myFile = new ArrayList<File>();;
    private List<String> fileName = new ArrayList<String>();
    private List<String> imageFileName = new ArrayList<String>();
    
    
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
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	public List<File> getMyFile() {
		return myFile;
	}
	public void setMyFile(List<File> myFile) {
		this.myFile = myFile;
	}
	public List<String> getFileName() {
		return fileName;
	}
	public void setFileName(List<String> fileName) {
		this.fileName = fileName;
	}
	public List<String> getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(List<String> imageFileName) {
		this.imageFileName = imageFileName;
	}
	
	
	public String list(){
		Map<String , Object> session = ActionContext.getContext().getSession();
		List<Product> list = productService.findAll();
		session.put("products",	 list);
		return "SUCCESS";
	}
	
	public String show(){
		Map session=ActionContext.getContext().getSession();
		List<Product> products = productService.findByCategoryId(categoryId);
		session.put("products", products);
		return "SUCCESS";
	}
	
	public String detail(){
		Map session=ActionContext.getContext().getSession();
		product = productService.findById(productId);
		session.put("product", product);
		return "SUCCESS";
	}
	
	public String search(){
		Map session = ActionContext.getContext().getSession();
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
	
	public String add(){
		Map<String , Object> session = ActionContext.getContext().getSession();
		List<Product> list = productService.findAll();
		
		String[] str = { "ou", "in", "fr", "cu","bk" };
		for(String s : str){
//				imageFileName = list.size()+"_"+s+"_l"+getExtention(fileName);
				File imageFile = new File(ServletActionContext.getServletContext().getRealPath( " /front/images/content/product/"+list.size() ) + " / " + imageFileName);
//				copy(myFile, imageFile);
		}
		return null;
	}
	
	private static void copy(File src, File dst) {
        try {
           InputStream in = null ;
           OutputStream out = null ;
            try {                
               in = new BufferedInputStream( new FileInputStream(src), BUFFER_SIZE);
               out = new BufferedOutputStream( new FileOutputStream(dst), BUFFER_SIZE);
                byte [] buffer = new byte [BUFFER_SIZE];
                while (in.read(buffer) > 0 ) {
                   out.write(buffer);
               } 
           } finally {
                if ( null != in) {
                   in.close();
               } 
                if ( null != out) {
                   out.close();
               } 
           } 
       } catch (Exception e) {
           e.printStackTrace();
       } 
   } 
	
	 private static List<String> getExtention(List<String> fileName) {
		 List<String> extensionList = new ArrayList<String>();
         for(String str: fileName){
        	 int pos = str.lastIndexOf( " . " );
        	  String extension = str.substring(pos);
        	  extensionList.add(extension);
         }
         return extensionList;
    } 
	
}
