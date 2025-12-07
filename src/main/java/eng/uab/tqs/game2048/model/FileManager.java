package eng.uab.tqs.game2048.model;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileManager {

  public boolean exists(String path) throws IOException {
    return Files.exists(Paths.get(path));
  }

  public long size(String path) throws IOException {
    return Files.size(Paths.get(path));
  }

  public List<String> readAll(String path) throws IOException {
    return Files.readAllLines(Paths.get(path));
  }

  public void writeAll(String path, List<String> lines) throws IOException {
    Files.write(Paths.get(path), lines);
  }

}