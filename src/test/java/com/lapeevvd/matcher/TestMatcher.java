package com.lapeevvd.matcher;

import com.lapeevvd.controller.json.JsonUtil;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

abstract public class TestMatcher<T> extends BaseMatcher<String> {
    protected T expected;

    public TestMatcher(T expected) {
        this.expected = expected;
    }

    @Override
    public boolean matches(Object actual) {
        return compare(expected, (String) actual);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText(JsonUtil.writeValue(expected));
    }

    abstract protected boolean compare(T expected, String actual);
}