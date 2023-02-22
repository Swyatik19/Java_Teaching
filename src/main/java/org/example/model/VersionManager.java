package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class VersionManager {
    private int count;
    private List<String> listDataVersion = new ArrayList<>();
    private static String version;

    public VersionManager(String version) {
        this.version = version;
    }

    public VersionManager() {
    }
    public List<String> beginnerVersion(){
        if (version.isEmpty()){
            listDataVersion.add("0.0.1");
        }
        return listDataVersion;
    }

    public void parsingVersion() {
        String[] str = version.split("\\.");
        for (int i = 0; i < version.length()+1; i++) {
            try {
                Integer.parseInt(str[i]);
            } catch (NumberFormatException e) {
                throw new RuntimeException("Сталася помилка під час аналізу версії!");
            }
        }
        count = str.length;
    }

    public List<String> versionControl() {
        beginnerVersion();
        parsingVersion();
        switch (count) {
            case 1:
                return major();
            case 2:
                return minor();
            case 3:
                return patch();
        }
        return null;
    }

    public List<String> major() {
        version = version + ".0.0";
        listDataVersion.add(version);
        return listDataVersion;
    }

    public List<String> minor() {
        version = version + ".0";
        listDataVersion.add(version);
        return listDataVersion;
    }

    public List<String> patch() {
        listDataVersion.add(version);
        return listDataVersion;
    }

    public void rollback() {
        listDataVersion.remove(listDataVersion.size());

    }

    public String release() {
        return listDataVersion.get(listDataVersion.size());
    }

    public static void main(String[] args) {
        VersionManager versionManager = new VersionManager("4.2.6");
//        versionManager.dataVersion.add("1.32.43");
//        versionManager.dataVersion.add("2.33.4");
//        versionManager.dataVersion.add("3.32.5");
        versionManager.rollback();
        versionManager.versionControl();

        System.out.println(versionManager.listDataVersion);
        System.out.println(versionManager.release());
    }
}
//    У цьому ката ми будемо імітувати систему керування версіями програмного забезпечення.
//        Ви повинні реалізувати клас VersionManager.
//
//        1) Він повинен приймати необов’язковий параметр, який представляє початкову версію. Вхідні дані будуть мати один із таких форматів: "{MAJOR}", "{MAJOR}.{MINOR}", або "{MAJOR}.{MINOR}.{PATCH}".
//        "3", "3.12", "3.12.21"
//
//        2) Після PATCH можна надати більше значень, але їх слід ігнорувати.
//        "3.12.21.7465" - після 3.12.21 всі решта значення ігноруються.
//
//        3) Якщо ці 3 частини не є десятковими значеннями, виняток із повідомленням "Сталася помилка під час аналізу версії!" слід кинути.
//        Під правильними значеннями будемо рахувати додатні числа в блоках {MAJOR}.{MINOR}.{PATCH}.
//
//        4) Якщо початкова версія не надається або є порожнім рядком, використовуйте "0.0.1" за умовчанням.
//
//        5) Цей клас має підтримувати наступні методи, усі з яких мають бути зв’язаними (крім випуску):
//        - major() - збільшити MAJOR на 1, встановити MINOR і PATCH на 0 // "3.12.21" => "4.0.0"
//        - minor() - збільшити MINOR на 1, встановити PATCH на 0         // "3.12.21" => "3.13.0"
//        - patch() - збільшити PATCH на 1                                // "3.12.21" => "3.12.22"
//        - rollback() - повертає MAJOR, MINOR і PATCH їхні значення перед попереднім викликом major/minor/patch або створює виняток із повідомленням "Cannot rollback!" якщо немає версії для повернення. Має бути можливим кілька викликів rollback() і відновлення історії версій. // "3.12.22" => "3.12.21"
//        - release() - повертає рядок у форматі "{MAJOR}.{MINOR}.{PATCH}"
//
//        Нехай бінарна сила буде з вами!