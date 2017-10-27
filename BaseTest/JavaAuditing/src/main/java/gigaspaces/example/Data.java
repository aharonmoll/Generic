package gigaspaces.example;
import java.util.ArrayList;
import java.util.Date;

import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceRouting;

public class Data
{
    private String id;
    private Long type;
    Boolean valid;
    ArrayList list;
    Date normalDate;

	// need no arg constr
    public Data() {}

    @SpaceId(autoGenerate=false)
    public String getId() {return id;}
    public void setId(String id) {this.id = id;}

    @SpaceRouting
    public Long getType() {return type;}
    public void setType(Long type) {this.type = type;}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public ArrayList getList() {
		return list;
	}

	public void setList(ArrayList list) {
		this.list = list;
	}

	public Date getNormalDate() {
		return normalDate;
	}

	public void setNormalDate(Date normalDate) {
		this.normalDate = normalDate;
	}	
	
}