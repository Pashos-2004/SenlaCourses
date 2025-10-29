package subtask_4;

class Master {
    private int id;
    private String name;
    private String specialization;
    
    public Master(int id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }
    
    public int getId() { 
    	return id;
    }
    
    public String getName() {
    	return name;
    }
    
    public String getSpecialization() {
    	return specialization; 
    }
    
    public void setId(int id) { 
    	this.id = id;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public void setSpecialization(String specialization) {
    	this.specialization = specialization; 
    }
    
    @Override
    public String toString() {
        return "Master{id=" + id + ", name='" + name + "', specialization='" + specialization + "'}";
    }
}
