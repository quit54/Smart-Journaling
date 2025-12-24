import java.util.Date;
import java.util.UUID;

/**
 * 笔记实体类
 */
public class Note {
    private String id;
    private String title;
    private String content;
    private Date createdAt;
    private Date updatedAt;

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.id = UUID.randomUUID().toString();
    }

    // Getter 和 Setter 方法
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}




