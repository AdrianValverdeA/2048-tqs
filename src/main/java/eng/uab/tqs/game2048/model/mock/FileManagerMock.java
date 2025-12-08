package eng.uab.tqs.game2048.model.mock;

import eng.uab.tqs.game2048.model.FileManager;

import java.io.IOException;
import java.util.*;

public class FileManagerMock extends FileManager {

  private boolean exists = false;
  private List<String> storedLines = new ArrayList<>();
  private boolean pretendNonEmpty = false;
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

      case "two":
        exists = true;
        storedLines = List.of("TestUser:2000","Alice:1500");
        break;

      case "some":
        exists = true;
        storedLines = List.of("TestUser:2000","Alice:1500", "Peter:800", "Max:400");
        break;

      case "ten":
        exists = true;
        storedLines = List.of("TestUser:2000","Alice:1500", "Peter:800", "Max:400", "Alfred:915"
                            ,"Juanjo:700", "Carlos:2100","Marc:2888","Ludmila:700", "Carmen:350");
        break;

      case "INVALID_FILE":
        exists = true;
        pretendNonEmpty = true;
        storedLines = new ArrayList<>();
        break;

      case "ERROR_FILE":
        exists = true;
        storedLines = new ArrayList<>();
        pretendNonEmpty = true;
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
    if (pretendNonEmpty) return 10;
    return storedLines.isEmpty() ? 0 : 1;
  }

  @Override
  public List<String> readAll(String path) throws IOException {
    if (pretendNonEmpty && storedLines.isEmpty()) {
      throw new IOException("Simulated file read error");
    }
    return new ArrayList<>(storedLines);
  }

  @Override
  public void writeAll(String path, List<String> lines) {
    //we don't want to write in any files, it is a mock
  }
}
