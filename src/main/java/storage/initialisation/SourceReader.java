package storage.initialisation;

@FunctionalInterface
public interface SourceReader<DATA> {
    DATA readDataFromFile(String filePath) throws Exception;
}
