package com.sashafilth;

import com.sashafilth.avl_binary_search_tree.AvlBinarySearchTree;
import com.sashafilth.counting_sort.CountingSort;
import com.sashafilth.metric.Metric;
import com.sashafilth.tree.Tree;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {

        Map<Integer, Metric> result = new TreeMap<>();

        int dataSetCount = 100;
        for (int i = 0; i < dataSetCount; i++) {
            int numberOfElements = random(100000, 400000);

            Tree<Integer> avlTree = generateDataSet(numberOfElements);

            int value = random(0, 400000);
            Metric metric = new Metric(numberOfElements, value);
            insert(metric, avlTree);
            find(metric, avlTree);
            delete(metric, avlTree);
            result.put(metric.getLength(), metric);
            System.out.println("iteration finished " + i);
        }

        toXlsx(result);

//        int[] arrToSort = new int[]{6, 8, 3, -12, 34};
//
//        CountingSort countingSort = new CountingSort(arrToSort);
//        countingSort.sort();
    }

    private static Tree<Integer> generateDataSet(int numberOfElements) {
        Tree<Integer> res = new AvlBinarySearchTree<>();
        for (int i = 0; i < numberOfElements; i++) {
            res.insert(random(0, 400000));
        }
        return res;
    }

    private static int random(int min, int max) {
        Random rand = new Random();
        return min + rand.nextInt((max - min) + 1);
    }

    private static void insert(Metric metric, Tree<Integer> dataSet) {
        int value = metric.getData();
        long start = System.nanoTime();
        dataSet.insert(value);
        long end = System.nanoTime();
        metric.setInsert(end - start);
    }

    private static void find(Metric metric, Tree<Integer> dataSet) {
        int value = metric.getData();
        long start = System.nanoTime();
        dataSet.find(value);
        long end = System.nanoTime();
        metric.setFind(end - start);
    }

    private static void delete(Metric metric, Tree<Integer> dataSet) {
        int value = metric.getData();
        long start = System.nanoTime();
        dataSet.delete(value);
        long end = System.nanoTime();
        metric.setDelete(end - start);
    }

    private static void toXlsx(Map<Integer, Metric> map) {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("sheet1");

        XSSFRow headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("length");
        headerRow.createCell(1).setCellValue("insert, ns");
        headerRow.createCell(2).setCellValue("find, ns");
        headerRow.createCell(3).setCellValue("delete, ns");

        int rowNumber = 1;

        for(Map.Entry entry: map.entrySet()) {
            XSSFRow currentRow = sheet.createRow(rowNumber);
            Integer key = (Integer) entry.getKey();
            Metric value = (Metric) entry.getValue();
            currentRow.createCell(0).setCellValue(key);
            currentRow.createCell(1).setCellValue(value.getInsert());
            currentRow.createCell(2).setCellValue(value.getFind());
            currentRow.createCell(3).setCellValue(value.getDelete());
            rowNumber ++;
        }

        try {
            FileOutputStream file = new FileOutputStream("avl_metrics.xlsx");
            workbook.write(file);
            file.close();
            System.out.println("xlsx created");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
