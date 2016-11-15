package com.socnet.web;

public class Views {
    /**
     * View that represents general information about the entity
     * and doesn't cause class hierarchy traversal
     */
    public static class Summary {}

    /**
     * View that very often retrieved together and between the data
     * exists parent - children relationship
     */
    public static class WithChildren extends Summary {}

    /**
     * View that very often retrieved together and between the data
     * exists child - parent relationship
     */
    public static class WithParent extends Summary{}
}
