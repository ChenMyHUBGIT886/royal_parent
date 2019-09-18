package com.bbs.domain;

import java.io.Serializable;

public class Word implements Serializable {
    /**
     * `wordId` int(11) NOT NULL AUTO_INCREMENT,
     * `word` varchar(255) DEFAULT NULL COMMENT '敏感词',
     * `status` int(2) DEFAULT '0' COMMENT '是否启用',
     */
    private Integer wordId; //主键
    private String word; //敏感词
    private Integer status; //是否启用
    private String statusStr;

    public Integer getWordId() {
        return wordId;
    }

    public void setWordId(Integer wordId) {
        this.wordId = wordId;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusStr() {
        if (status != null) {
            if (status == 0) {
                statusStr = "已停用";
            } else if (status == 1) {
                statusStr = "使用中";
            }
        }
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    @Override
    public String toString() {
        return "Word{" +
                "wordId=" + wordId +
                ", word='" + word + '\'' +
                ", status=" + status +
                '}';
    }
}
