package classi;

public class Tag {
    private String nomeTag;
    private int id;

    public Tag(String nomeTag, int id) {
        this.nomeTag = nomeTag;
        this.id=id;
    }

    // getters and setters for all attributes
    public String getNomeTag() {
        return nomeTag;
    }

    public void setNomeTag(String nomeTag) {
        this.nomeTag = nomeTag;
    }

    // toString method to print the tag details
    @Override
    public String toString() {
        return "Tag{" +
                "nomeTag='" + nomeTag + '\'' +
                '}';
    }
}
