package com.deloitte.nextgen.framework.commons.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

/**
 * @author nishmehta on 27/10/2020 3:59 PM
 * @project ng-framework
 */

public class PagedData<T> {

    @Getter
    private final Page<T> page;

    private PagedData(PageMetadata metadata, List<T> content) {
        this.page = new Page<>(metadata, content);
    }

    public static <T> PageBuilder<T> with(int pageNumber, int pageSize, int totalPages, long totalRecords, int filteredRecords) {
        return new DefaultPageBuilder<>(pageNumber, pageSize, totalPages, totalRecords, filteredRecords);
    }

    @Data
    public static class PageMetadata {

        private int pageNumber;

        private int pageSize;

        private int totalPages;

        private long totalRecords;

        private int filteredRecords;

    }

    public interface PageBuilder<T> {
         PagedData<T> content(List<T> content);
         PagedData<T> build();
    }

    private static class DefaultPageBuilder<T> implements PageBuilder<T> {

        private final PageMetadata pageMetadata;

        public DefaultPageBuilder(int pageNumber, int pageSize, int totalPages, long totalRecords, int filteredRecords) {
            pageMetadata = new PageMetadata();
            pageMetadata.setPageNumber(pageNumber);
            pageMetadata.setPageSize(pageSize);
            pageMetadata.setTotalPages(totalPages);
            pageMetadata.setTotalRecords(totalRecords);
            pageMetadata.setFilteredRecords(filteredRecords);
        }

        @Override
        public  PagedData<T> content(List<T> content) {
            return new PagedData<>(pageMetadata, content);
        }

        @Override
        public  PagedData<T> build() {
            return content(null);
        }
    }

    @Data
    @AllArgsConstructor
    private static class Page<T> {

        private PageMetadata metadata;

        private List<T> content;

    }
}
