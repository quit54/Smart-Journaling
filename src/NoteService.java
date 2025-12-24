import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 笔记服务类
 */
public class NoteService {
    private NoteManager noteManager;
    private static final String NOTES_DIR = "notes";
    private static final String CONTENT_DIR = "content";

    public NoteService() {
        this.noteManager = new NoteManager();
        initializeDirectories();
    }

    public void createNote(Note note) {
        // 生成唯一ID
        String id = UUID.randomUUID().toString();
        note.setId(id);

        // 保存笔记元数据到JSON文件
        saveNoteMetadata(note);

        // 保存笔记内容到TXT文件
        saveNoteContent(note);

        // 添加到内存中的列表
        noteManager.addNote(note);
    }

    public List<Note> getAllNotes() {
        return noteManager.getAllNotes();
    }

    public List<Note> searchNotes(String keyword) {
        return noteManager.searchNotes(keyword);
    }

    public Note getNoteById(String id) {
        for (Note note : noteManager.getAllNotes()) {
            if (note.getId().equals(id)) {
                return note;
            }
        }
        return null;
    }

    private void initializeDirectories() {
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
}
