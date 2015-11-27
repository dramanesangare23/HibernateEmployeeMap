package pkg;

public class Certificate{
	private int id;
	private String name;

	public Certificate(){}

	public Certificate(String name){
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

//	/* (non-Javadoc)
//	 * @see java.lang.Object#hashCode()
//	 */
//
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + this.id;
//		result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
//		return result;
//	}
//
//	/* (non-Javadoc)
//	 * @see java.lang.Object#equals(java.lang.Object)
//	 */
//
//	@Override
//	public boolean equals(Object obj) {
//		//If both objects refer the same area in the memory
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (!(obj instanceof Certificate))
//			return false;
//
//		Certificate other = (Certificate) obj;
//		if ((this.id == other.getId()) && (this.name.equals(other.getName())))
//			return true;
//
//		return false;
//	}
//
//	@Override
//	public int compareTo(Certificate that) {
//		final int BEFORE = -1;
//		final int AFTER = 1;
//
//		if(that == null)
//			return BEFORE;
//		
//		String thisCertificate = this.getName();
//		String thatCertificate = that.getName();
//		
//		if(thisCertificate == null){
//			return AFTER;
//		} else if(thatCertificate == null){
//			return BEFORE;
//		} else{
//			return thisCertificate.compareTo(thatCertificate);
//		}
//	}

}
