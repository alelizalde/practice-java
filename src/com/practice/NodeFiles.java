package com.practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class NodeFiles {

    static class FileNode {

        boolean isDirectory;
        String name;
        int size;
        boolean compressed;
        List<FileNode> children;

        FileNode(boolean isDirectory, String name, int size, boolean compressed) {
            this.isDirectory = isDirectory;
            this.name = name;
            this.size = size;
            this.compressed = compressed;
            children = new ArrayList<>();
        }
    }

    interface Criteria {
        boolean get(FileNode node);
    }

    static class SearchExtension implements Criteria {

        String search;
        SearchExtension (String search) {
            this.search = search;
        }

        public boolean get(FileNode node) {
            return node.name.contains(search);
        }
    }

    static class SearchBySize implements Criteria {

        int search;
        SearchBySize (int search) {
            this.search = search;
        }

        public boolean get(FileNode node) {
            return node.size > search;
        }
    }

    static private List<FileNode> findFiles(FileNode root, Criteria search) {
        Stack<FileNode> stack = new Stack<>();
        List<FileNode> result = new ArrayList<>();

        stack.push(root);
        while (!stack.isEmpty()) {
            FileNode current = stack.pop();
            if (current.isDirectory) {
                for (FileNode node : current.children) {
                    stack.push(node);
                }

            } else {
                if (search.get(current)) {
                    result.add(current);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        FileNode root = new FileNode(true, "/", 1, false);
        FileNode folder1 = new FileNode(true, "folder1", 2, false);
        FileNode folder2 = new FileNode(true, "folder2", 2, false);
        FileNode folder3 = new FileNode(true, "folder3", 2, false);
        FileNode file1 = new FileNode(false, "file1", 100, true);
        FileNode file2 = new FileNode(false, "file2", 500, false);
        FileNode file3 = new FileNode(false, "file3", 10000, false);
        root.children.add(folder1);
        root.children.add(folder2);
        folder1.children.add(file1);
        folder2.children.add(folder3);
        folder2.children.add(file2);
        folder3.children.add(file3);
        Criteria searchExtension = new SearchExtension("file1");
        findFiles(root, searchExtension).forEach(result -> {
            System.out.println("search extension [" + result.name + "]");
                }
        );
        Criteria searchBySize = new SearchBySize(100);
        findFiles(root, searchBySize).forEach(result -> {
                    System.out.println("search size [" + result.size + "]");
                }
        );

        findFiles(root, (fileNode) -> fileNode.name.contains("file1")).forEach(
                result -> {
                    System.out.println("search name lambda [" + result.name + "]");
                }
        );

        findFiles(root, (fileNode) -> fileNode.size > 100).forEach(
                result -> {
                    System.out.println("search size lambda [" + result.size + "]");
                }
        );

    }
}
