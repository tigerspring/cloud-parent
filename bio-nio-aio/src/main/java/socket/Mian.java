package socket;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.DoubleToIntFunction;
import java.util.stream.Collectors;

public class Mian {

    public static void main(String[] args) throws IOException {
        List<Path> pathList = Files.list(Paths.get("E:\\数据导出\\0923\\创意视频\\切割文件")).collect(Collectors.toList());


        Files.walkFileTree(Paths.get("E:\\数据导出\\0923\\创意视频\\切割文件"), new FileVisitor(){

            @Override
            public FileVisitResult preVisitDirectory(Object dir, BasicFileAttributes attrs) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
                File tmpfile = new File(file.toString());
                tmpfile.renameTo(new File(file + ".csv"));
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Object file, IOException exc) throws IOException {
                return FileVisitResult.TERMINATE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Object dir, IOException exc) throws IOException {
                return FileVisitResult.TERMINATE;
            }
        });


    }

    public void doAction(Set<String> set,Object file){
        //第一步：先获取csv文件的路径，通过BufferedReader类去读该路径中的文件
        File csv = new File(file.toString());

        try{
            //第二步：从字符输入流读取文本，缓冲各个字符，从而实现字符、数组和行（文本的行数通过回车符来进行判定）的高效读取。
            BufferedReader textFile = new BufferedReader(new FileReader(csv));
            String lineDta = "";

            //第三步：将文档的下一行数据赋值给lineData，并判断是否为空，若不为空则输出
            while ((lineDta = textFile.readLine()) != null){
                String resId = lineDta.replaceAll("\"","").split(",")[0];
                set.add(resId);
            }

        }catch (IOException e){
            System.out.println("文件读写出错");
        }
        System.out.println(file);
    }

}
