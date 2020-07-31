package com.kyy.recuperationcourt.common.model.constant;

public enum MultiPage {

    热点(0),
    养生(1),
    食疗(2),
    慢性病(3),
    母婴(4),
    美妆(5),
    减重(6),
    心理(7),
    澄清解答(4);

    private final int position;

    MultiPage(int pos) {
        position = pos;
    }

    public static MultiPage getPage(int position) {
        return MultiPage.values()[position];
    }

    public static int size() {
        return MultiPage.values().length;
    }

    public static String[] getPageNames() {
        MultiPage[] pages = MultiPage.values();
        String[] pageNames = new String[pages.length];
        for (int i = 0; i < pages.length; i++) {
            pageNames[i] = pages[i].name();
        }
        return pageNames;
    }

    public int getPosition() {
        return position;
    }

}