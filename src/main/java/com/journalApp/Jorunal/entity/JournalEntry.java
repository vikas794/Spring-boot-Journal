package com.journalApp.Jorunal.entity;
import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Document // (collection = "your_mongo_DB_collection_name") // entire this to select the collection name
// @Getter
// @Setter
@Data // contains inbuilt setters and getters and no args and args constructors
@NoArgsConstructor
public class JournalEntry {
    
    @Id
    private ObjectId id;

    @NonNull
    private String title;
    private String content;
    
    private LocalDateTime date;
    
}
