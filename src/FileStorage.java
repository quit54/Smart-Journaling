import java.io.*;
import java.util.UUID;

public class FileStorage {
    private static final String NOTES_DIR = "notes";
    private static final String CONTENT_DIR = "content";

    public void saveNote(Note note) {
        createDirectories();

        // 保存笔记元数据到JSON文件
        saveNoteMetadata(note);

        // 保存笔记内容到TXT文件
        saveNoteContent(note);
    }

    private void createDirectories() {
        File notesDir = new File(NOTES_DIR);
        if (!notesDir.exists()) {
            notesDir.mkdirs();
        }

        File contentDir = new File(CONTENT_DIR);
        if (!contentDir.exists()) {
            contentDir.mkdirs();
        }
    }

    private void saveNoteMetadata(Note note) {
        String fileName = NOTES_DIR + "/" + note.getId() + ".json";
        try (FileWriter writer = new FileWriter(fileName)) {
            // 使用Gson或其他JSON库序列化对象
            writer.write("{\"id\":\"" + note.getId() + "\",\"title\":\"" + note.getTitle() +
                    "\",\"createdAt\":\"" + note.getCreatedAt() + "\",\"updatedAt\":\"" +
                    note.getUpdatedAt() + "\"}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveNoteContent(Note note) {
        String fileName = CONTENT_DIR + "/" + note.getId() + ".txt";
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(note.getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Note loadNote(String id) {
        String metadataFile = NOTES_DIR + "/" + id + ".json";
        String contentFile = CONTENT_DIR + "/" + id + ".txt";

        try {
            // 加载笔记元数据
            Note note = loadNoteMetadata(metadataFile);

            // 加载笔记内容
            loadNoteContent(contentFile, note);

            return note;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Note loadNoteMetadata(String filePath) throws IOException {
        // 实现从JSON文件加载笔记元数据
        return null;
    }

    private void loadNoteContent(String filePath, Note note) throws IOException {
        // 实现从TXT文件加载笔记内容
    }
}
