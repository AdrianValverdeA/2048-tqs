package eng.uab.tqs.game2048.model.mock;

import eng.uab.tqs.game2048.model.FileManager;

import java.io.IOException;
import java.util.*;

public class FileManagerMock extends FileManager {

  private boolean exists = false;
  private List<String> storedLines = new ArrayList<>();

  public void setConfig(String config) {

    switch (config) {

      case "NO_FILE":
        exists = false;
        storedLines = new ArrayList<>();
        break;

      case "EMPTY_FILE":
        exists = true;
        storedLines = new ArrayList<>();
        break;

      case "ONE_SCORE":
        exists = true;
        storedLines = List.of("TestUser:400");
        break;

      case "TWO_SCORES":
        exists = true;
        storedLines = List.of("TestUser:2000","Alice:1500", "Bob:900");
        break;

      default:
        throw new IllegalArgumentException("Unknown config: " + config);
    }
  }

  @Override
  public boolean exists(String path) {
    return exists;
  }

  @Override
  public long size(String path) {
    return storedLines.isEmpty() ? 0 : 1;
  }

  @Override
  public List<String> readAll(String path) throws IOException {
    return new ArrayList<>(storedLines);
  }

  @Override
  public void writeAll(String path, List<String> lines) {
    //we don't want to write in any files, it is a mock
  }
}
