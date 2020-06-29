package study;

/**
 * 设备型号编号，供应设备的型号编号
 * 
 * @author yuyifang
 * 
 * @date 2019-03-19
 */
public class DevModel {


	protected Integer id;
	private String devFactoryId;
	
	private DevFactory devFactory;

	private String devModelId;

	private String devModelName;

	private String devModelPic;

	private String devProdParam;
	
	private String devType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDevFactoryId() {
		return devFactoryId;
	}

	public void setDevFactoryId(String devFactoryId) {
		this.devFactoryId = devFactoryId == null ? null : devFactoryId.trim();
	}

	public String getDevModelId() {
		return devModelId;
	}

	public void setDevModelId(String devModelId) {
		this.devModelId = devModelId == null ? null : devModelId.trim();
	}

	public String getDevModelName() {
		return devModelName;
	}

	public void setDevModelName(String devModelName) {
		this.devModelName = devModelName == null ? null : devModelName.trim();
	}

	public String getDevModelPic() {
		return devModelPic;
	}

	public void setDevModelPic(String devModelPic) {
		this.devModelPic = devModelPic == null ? null : devModelPic.trim();
	}

	public String getDevProdParam() {
		return devProdParam;
	}

	public void setDevProdParam(String devProdParam) {
		this.devProdParam = devProdParam == null ? null : devProdParam.trim();
	}

	public String getDevType() {
		return devType;
	}

	public void setDevType(String devType) {
		this.devType = devType;
	}

	public DevFactory getDevFactory() {
		return devFactory;
	}

	public void setDevFactory(DevFactory devFactory) {
		this.devFactory = devFactory;
	}
}