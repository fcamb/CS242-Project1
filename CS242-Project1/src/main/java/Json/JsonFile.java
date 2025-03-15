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

/**
 *      class JsonFile
 *      represents a JSON file and provides methods to read, write, and serialize JSON data
 *      @author Frank Cambria      
 */
class JsonFile
{
        // file path of the JSON file
        private Path filePath;
        // name of the file
        private String fileName;
        // extension of the file
        private String fileExtension;
        // size of the file in bytes
        private long fileSize;

        // string representation of the file contents
        private String fileString;
        // raw bytes of the file contents
        private byte[] fileBytes;

        /**
         *       public JsonFile()
         *       base constructor
         */
        public JsonFile()
        {}

        /**
         *       private boolean FileAlreadyOpen(String fileName)
         *       checks if the specified file is already open
         *       @param String fileName
         *       @return boolean
         */
        private boolean FileAlreadyOpen(String fileName)
        {
                if (this.fileName != null && this.fileName.equals(fileName))
                {
                        return true;
                }
                return false;
        }

        /**
         *       private void GetFileExtension()
         *       extracts the file extension from the file name
         *       @return void
         */
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

        /**
         *       private void GetFileSize()
         *       throws IOException
         *       retrieves the size of the file in bytes
         *       @return void
         */
        private void GetFileSize() throws IOException
        {
                fileSize = Files.size(filePath);
        }

        /**
         *       private void GetFileName()
         *       extracts the file name from the file path
         *       @return void
         */
        private void GetFileName()
        {
                fileName = filePath.getFileName().toString();
        }

        /**
         *       private void DoesFileExist()
         *       throws IOException
         *       checks if the file exists, throws exception if not
         *       @return void
         */
        private void DoesFileExist() throws IOException
        {
                if (!Files.exists(filePath))
                {
                        throw new IOException("Current Dir: " + Paths.get("").toAbsolutePath().toString() + " " + filePath + " does not exist");
                }
        }

        /**
         *       private void ReadFileAsString()
         *       reads the file contents into a string
         *       @return void
         */
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

        /**
         *       private void ReadFileAsBytes()
         *       reads the file contents into a byte array
         *       @return void
         */
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

        /**
         *       private void OpenFile(String fileName)
         *       opens the specified file and initializes its metadata
         *       @param String fileName
         *       @return void
         */
        private void OpenFile(String fileName) 
        {
                if (FileAlreadyOpen(fileName))
                {
                        System.out.println("File already opened");
                }
                else
                {
                        filePath = Paths.get(fileName).toAbsolutePath();
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

        /**
         *       public void OpenAndReadJsonFileAsString(String file)
         *       opens and reads the JSON file as a string
         *       @param String file
         *       @return void
         */
        public void OpenAndReadJsonFileAsString(String file)
        {
                OpenFile(file);
                ReadFileAsString();
        }

        /**
         *       private void OpenAndReadJsonFileAsBytes(String file)
         *       opens and reads the JSON file as a byte array
         *       @param String file
         *       @return void
         */
        private void OpenAndReadJsonFileAsBytes(String file)
        {
                OpenFile(file);
                ReadFileAsBytes();
        }

        /**
         *       private byte[] SerializeJsonDataObject(JsonData jsonData)
         *       throws IOException
         *       serializes the JsonData object into a byte array
         *       @param JsonData jsonData
         *       @return byte[]
         */
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

        /**
         *       private void WriteSerializedJsonData(byte[] serializedJsonData, String filePath)
         *       throws IOException
         *       writes the serialized JSON data to the specified file
         *       @param byte[] serializedJsonData
         *       @param String filePath
         *       @return void
         */
        private void WriteSerializedJsonData(byte[] serializedJsonData, String filePath)
                throws IOException
        {
                Path path = Paths.get(filePath);
                Files.write(path, serializedJsonData);
        }

        /**
         *       private void PrintSerializedFileContents()
         *       throws IOException
         *       prints the first 10 bytes of the serialized file contents in hexadecimal
         *       @return void
         */
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

        /**
         *       private void PrintSerializedHeader(byte[] SerializedObject)
         *       prints the first 4 bytes of the serialized object in hexadecimal
         *       @param byte[] SerializedObject
         *       @return void
         */
        private void PrintSerializedHeader(byte[] SerializedObject)
        {
                System.out.println("Serialized bytes (first 4): " + 
                                String.format("%02X %02X %02X %02X", 
                                SerializedObject[0], SerializedObject[1], SerializedObject[2], SerializedObject[3]));
        }

        /**
         *       public void WriteSerializedJsonDataToFile(JsonData jsonData, String filePath)
         *       serializes and writes JSON data to the specified file
         *       @param JsonData jsonData
         *       @param String filePath
         *       @return void
         */
        public void WriteSerializedJsonDataToFile(JsonData jsonData, String filePath)
        {
                try
                {
                        byte[] SerializedObject = SerializeJsonDataObject(jsonData);                        
                        WriteSerializedJsonData(SerializedObject, filePath);
                }
                catch(IOException e)
                {
                        System.out.println("IOException while writing: " + e.getMessage());
                }
        }

        /**
         *       private JsonObject DeserializeJsonDataObject()
         *       throws Exception
         *       deserializes the file bytes into a JsonObject
         *       @return JsonObject
         */
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

        /**
         *       public void ReadSerializedJsonDataFromFile(String filePath)
         *       reads serialized JSON data from the specified file into bytes
         *       @param String filePath
         *       @return void
         */
        public void ReadSerializedJsonDataFromFile(String filePath)
        {                
                OpenAndReadJsonFileAsBytes(filePath);
        }

        /**
         *       public String FileDataString()
         *       returns the file contents as a string
         *       @return String
         */
        public String FileDataString()
        {
                return fileString;
        }

        /**
         *       public byte[] FileDataRawBytes()
         *       returns the raw bytes of the file contents
         *       @return byte[]
         */
        public byte[] FileDataRawBytes()
        {
                return fileBytes;
        }

        /**
         *       public JsonData FileDataSerializedBytes()
         *       deserializes the file bytes into a JsonData object
         *       @return JsonData
         */
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
