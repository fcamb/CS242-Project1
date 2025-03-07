import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

public class FileOp
{
        private Path filePath;
        private String fileName;
        private String fileExtension;
        private long fileSize;

        private String fileString;
        private byte[] fileBytes;

        public FileOp()
        {}

        private boolean FileAlreadyOpen()
        {
                if (this.fileName != null && this.fileName.equals(fileName))
                {
                        return true;
                }
                return false;
        }

        private void GetFileExtension()
        {
                int lastDot = fileName.lastIndexOf('.');
                if (lastDot == -1 || lastDot == fileName.length() - 1)
                {
                        fileExtension = "";
                }
                else
                {
                        fileExtension = fileName.substring(lastDot);
                }
        }

        private void GetFileSize() throws IOException
        {
                fileSize = Files.size(filePath);
        }

        private void GetFileName()
        {
                fileName = filePath.getFileName().toString();
        }

        private void DoesFileExist() throws IOException
        {
                if (!Files.exists(filePath))
                {
                        throw new IOException(filePath + " does not exist");
                }
        }

        private void ReadFileAsString()
        {
                try
                {
                        fileString = new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);
                }
                catch (IOException e)
                {
                        System.out.println("Error reading file as a string: " + e.getMessage());
                }
        }

        private void ReadFileAsBytes()
        {                
                try
                {
                        fileBytes = Files.readAllBytes(filePath);
                }
                catch (IOException e)
                {
                        System.out.println("Error reading file as bytes: " + e.getMessage());
                }
        }

        private void OpenFile(String fileName) 
        {
                if (FileAlreadyOpen())
                {
                        System.out.println("File already opened");
                }
                else
                {
                        filePath = Paths.get(fileName);
                        try
                        {                                
                                DoesFileExist();
                                GetFileName();
                                GetFileExtension();
                                GetFileSize();
                        }
                        catch (IOException e)
                        {
                                System.out.println(e.getMessage());
                        }
                }
        }

        public void OpenAndReadFileAsString(String file)
        {
                OpenFile(file);
                ReadFileAsString();
        }

        public void OpenAndReadFileAsBytes(String file)
        {
                OpenFile(file);
                ReadFileAsBytes();
        }

        public String FileDataString()
        {
                return fileString;
        }

        public byte[] FileDataBytes()
        {
                return fileBytes;
        }

        public void ConvertFileDataBytesToString()
        {
                fileString = new String(fileBytes);
        }

        public void ConvertFileDataStringToBytes()
        {
                fileBytes = fileString.getBytes(StandardCharsets.UTF_8);
        }
}
