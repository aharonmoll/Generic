package com.gigaspaces;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceDynamicProperties;
import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceRouting;
import com.gigaspaces.document.DocumentProperties;

import java.io.Serializable;

/**
 * Created by Aharon on 02-Feb-15.
 */
@SpaceClass
public class Data implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private boolean processed;

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    private Long type;

    public void Data(){

    }

    @SpaceId(autoGenerate = true)
    @SpaceRouting()
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private DocumentProperties extendedProperties;

    // constructors, ID and routing properties, etc...

    @SpaceDynamicProperties
    public DocumentProperties getExtendedProperties() {
            return extendedProperties;
    }

    public void setExtendedProperties(DocumentProperties extendedProperties) {
            this.extendedProperties = extendedProperties;
    }

    public boolean isProcessed() {return processed;}
    public void setProcessed(boolean processed) {this.processed = processed;}
}
