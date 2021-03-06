package eu.neoteric.starter.mongo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.common.base.MoreObjects;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;

@Document(collection = "FooModel")
public class FooModel {

    public static final String NAME = "name";
    public static final String COUNT = "count";
    public static final String DATE = "date";
    public static final String TAGS = "tags";

    @JsonProperty(NAME)
    private final String name;

    @JsonProperty(COUNT)
    private final Integer count;

    @JsonProperty(DATE)
    private final ZonedDateTime date;

    @JsonProperty(TAGS)
    private final List<String> tags;

    private int cachedHashCode;

    @PersistenceConstructor
    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public FooModel(@JsonProperty(NAME) String name, @JsonProperty(COUNT) Integer count,
                    @JsonProperty(DATE) ZonedDateTime date, @JsonProperty(TAGS) List<String> tags) {
        this.name = name;
        this.count = count;
        this.date = date;
        this.tags = tags;
        this.cachedHashCode = calculateHashCode();
    }

    public FooModel(Builder builder) {
        this.name = builder.name;
        this.count = builder.count;
        this.date = builder.date;
        this.tags = builder.tags;

        this.cachedHashCode = calculateHashCode();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public Integer getCount() {
        return count;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public List<String> getTags() {
        return tags;
    }

    @Override
    public final int hashCode() {
        return this.cachedHashCode;
    }

    private int calculateHashCode() {
        return Objects.hash(name, count, date, tags);
    }

    @Override
    @SuppressWarnings({"squid:MethodCyclomaticComplexity", "squid:S1067"})
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FooModel)) {
            return false;
        }
        FooModel other = (FooModel) obj;
        return Objects.equals(this.name, other.name)
                && Objects.equals(this.count, other.count)
                && Objects.equals(this.date, other.date)
                && Objects.equals(this.tags, other.tags);
    }

    @JsonPOJOBuilder(withPrefix = "set")
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Builder {

        @JsonProperty(NAME)
        private String name;

        @JsonProperty(COUNT)
        private Integer count;

        @JsonProperty(DATE)
        private ZonedDateTime date;

        @JsonProperty(TAGS)
        private List<String> tags;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setCount(Integer count) {
            this.count = count;
            return this;
        }

        public Builder setDate(ZonedDateTime date) {
            this.date = date;
            return this;
        }

        public Builder setTags(List<String> tags) {
            this.tags = tags;
            return this;
        }

        public Builder copy(FooModel other) {
            return this
                    .setName(other.name)
                    .setCount(other.count)
                    .setDate(other.date)
                    .setTags(other.tags);
        }

        public FooModel build() {
            return new FooModel(this);
        }
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("count", count)
                .add("date", date)
                .add("tags", tags)
                .add("cachedHashCode", cachedHashCode)
                .toString();
    }
}