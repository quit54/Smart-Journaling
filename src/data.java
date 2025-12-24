import java.util.Date;
import java.util.List;

public class data {
    // 需要实现的 Note 类
    static class Note {
        private String title;
        private String content;
        private Date createdAt;

        // 构造函数、getter/setter 方法
        public Note(String title, String content) {
            this.title = title;
            this.content = content;
            this.createdAt = new Date();
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

    }
    static class NoteManager {
        private List<Note> notes;

        public void addNote(Note note) {
            //增加笔记部分代码编写
        }

        public List<Note> getAllNotes() {
            return notes;
        }

        public List<Note> searchNotes(String keyword) {
            return notes;
        }

        // 添加、获取、搜索笔记的方法
    }
}
