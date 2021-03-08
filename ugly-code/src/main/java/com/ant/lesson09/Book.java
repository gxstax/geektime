package com.ant.lesson09;

/**
 * <p>
 * 图书实体
 * </p>
 *
 * @author Ant
 * @since 2021/1/24 3:25 下午
 */
public class Book {

    /**
     * 图书ID
     */
    private long bookId;

    /**
     * 标题
     */
    private String title;
    
    /**
     * 简介
     */
    private String introduction;

    /**
     * 状态
     */
    private ReviewStatus reviewStatus;

    public Book() {}

    /**
     * <p>
     * 利用构造函数取代 setter 赋值
     * </p>
     * 
      * @param bookId
     * @param title
     * @param introduction
     * @return
     */
    public Book(long bookId, String title, String introduction) {
        this.bookId = bookId;
        this.title = title;
        this.introduction = introduction;
    }

    public Book(Builder builder) {
        this.bookId = builder.bookId;
        this.title = builder.title;
        this.introduction = builder.introduction;
        this.reviewStatus = builder.reviewStatus;
    }

    /**
     * <p>
     * 审核图书
     * </p>
     * 
      * @param 
     * @return void
     */
    public void approve() {
        this.reviewStatus = ReviewStatus.APPROVED;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", introduction='" + introduction + '\'' +
                ", reviewStatus=" + reviewStatus +
                '}';
    }


    public static class Builder {
        /**
         * 图书ID
         */
        private long bookId;

        /**
         * 标题
         */
        private String title;

        /**
         * 简介
         */
        private String introduction;

        /**
         * 状态
         */
        private ReviewStatus reviewStatus;

        public Builder id(long bookId) {
            this.bookId = bookId;
            return this;
        }

        public Builder name(String title) {
            this.title = title;
            return this;
        }

        public Builder type(String introduction) {
            this.introduction = introduction;
            return this;
        }

        public Builder price(ReviewStatus reviewStatus) {
            this.reviewStatus = reviewStatus;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }

}
