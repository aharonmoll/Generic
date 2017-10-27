import com.gigaspaces.annotation.pojo.SpaceId;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity    
public class MyPojo {
    	String id;
    	String data;
        public MyPojo(){
            
        }
        
        public MyPojo(String id, String data) {
			super();
			this.id = id;
			this.data = data;
		}

		
        @SpaceId
        @Id
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getData() {
            return data;
        }
        public void setData(String data) {
            this.data = data;
        }
        
        
        
        public MyPojo(String id){
            this.id = id;
        }
        
    }
