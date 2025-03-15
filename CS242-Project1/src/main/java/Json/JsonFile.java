package Json;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

public class JsonFile
{
        private Path filePath;
        private String fileName;
        private String fileExtension;
        private long fileSize;

        private String fileString;
        private byte[] fileBytes;

        public JsonFile()
        {}

        private boolean FileAlreadyOpen(String fileName)
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
                        throw new IOException("Current Dir: " + Paths.get("").toAbsolutePath().toString() + " " + filePath + " does not exist");
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
                if (FileAlreadyOpen(fileName))
                {
                        System.out.println("File already opened");
                }
                else
                {
                        filePath = Paths.get(fileName).toAbsolutePath();
                        // filePath.toAbsolutePath();
                        // this.fileName = fileName;
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

        public void OpenAndReadJsonFileAsString(String file)
        {
                OpenFile(file);
                ReadFileAsString();
        }

        private void OpenAndReadJsonFileAsBytes(String file)
        {
                OpenFile(file); // does it need to be opened
                ReadFileAsBytes();
        }

        private byte[] SerializeJsonDataObject(JsonData jsonData)
                throws IOException
        {
                try (ByteArrayOutputStream ByteStream = new ByteArrayOutputStream();
                     ObjectOutputStream OutputStream = new ObjectOutputStream(ByteStream))
                {
                        OutputStream.writeObject(jsonData.BaseObject());
                        return ByteStream.toByteArray();
                }
        }

        private void WriteSerializedJsonData(byte[] serializedJsonData, String filePath)
                throws IOException
        {
                Path path = Paths.get(filePath);
                Files.write(path, serializedJsonData);
        }

        private void PrintSerializedFileContents()
                throws IOException
        {
                byte[] bytes = Files.readAllBytes(filePath);
                StringBuilder hex = new StringBuilder();
                for (int i = 0; i < Math.min(bytes.length, 10); i++) {
                    hex.append(String.format("%02X ", bytes[i]));
                }
                System.out.println("File contents (first 10 bytes): " + hex.toString());
        }

        private void PrintSerializedHeader(byte[] SerializedObject)
        {
                System.out.println("Serialized bytes (first 4): " + 
                                String.format("%02X %02X %02X %02X", 
                                SerializedObject[0], SerializedObject[1], SerializedObject[2], SerializedObject[3]));
        }

        public void WriteSerializedJsonDataToFile(JsonData jsonData, String filePath)
        {
                try
                {
                        byte[] SerializedObject = SerializeJsonDataObject(jsonData);                        
                        // PrintSerializedHeader(SerializedObject);
                        WriteSerializedJsonData(SerializedObject, filePath);
                        // PrintSerializedFileContents();
                }
                catch(IOException e)
                {
                        System.out.println("IOException while writing: " + e.getMessage());
                }

        }

        private JsonObject DeserializeJsonDataObject()
                throws Exception
        {
                if (fileBytes == null || fileBytes.length == 0)
                {
                        throw new Exception("Serialized data has not been read from file.");
                }

                try (ByteArrayInputStream ByteStream = new ByteArrayInputStream(fileBytes);
                     ObjectInputStream Input = new ObjectInputStream(ByteStream))
                {
                        return (JsonObject) Input.readObject();
                }
                catch (ClassNotFoundException e)
                {
                        throw new RuntimeException(e);
                }
        }

        public void ReadSerializedJsonDataFromFile(String filePath)
        {                
                OpenAndReadJsonFileAsBytes(filePath);
        }

        public String FileDataString()
        {
                return fileString;
        }

        public byte[] FileDataRawBytes()
        {
                return fileBytes;
        }

        public JsonData FileDataSerializedBytes()
        {
                JsonData jsonData = new JsonData();
                try
                {
                        JsonObject dataObject = DeserializeJsonDataObject();
                        jsonData.SetData(dataObject);
                }
                catch (IOException ioException)
                {
                        System.out.println("IOException while reading: " + ioException.getMessage());
                        System.exit(1);
                }
                catch (ClassNotFoundException classException)
                {
                        System.out.println("ClassNotFoundException while reading: " + classException.getMessage());
                        System.exit(1);
                }
                catch (RuntimeException runtimeException)
                {
                        System.out.println("RuntimeException while reading" + runtimeException.getMessage());
                        System.exit(1);
                }
                catch (Exception e)
                {
                        System.out.println("Exception while reading:" + e.getMessage());
                        System.exit(1);
                }

                return jsonData;
        }
}
