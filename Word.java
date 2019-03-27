class Word{
    // private variables 
    private String w;
    private String ID;
    
    //constructor
    public Word(String s, String x)
    {
    	setW(s);
    	setID(x);
    	//setIndex(i);
    }
    // methods 

	public String toString() {
		return w;
	}

	public void setW(String w) {
		this.w = w;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}
	
	public void info()
	{
		System.out.println(w + "; " + w.length() + "; " + getID());
	}
}
