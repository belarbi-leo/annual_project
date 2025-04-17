import { useCallback, useState } from "react";
import { useDropzone } from "react-dropzone";
import { CloudArrowUpIcon } from "@heroicons/react/24/outline";

type ImageUploadProps = {
  onFileChange: (file: File | null) => void;
  label: string;
};

const ImageUpload = ({ onFileChange, label }: ImageUploadProps) => {
  const [file, setFile] = useState<File | null>(null);

  const onDrop = useCallback((acceptedFiles: File[]) => {
    if (acceptedFiles.length > 0) {
      setFile(acceptedFiles[0]);
      onFileChange(acceptedFiles[0]);
    }
  }, [onFileChange]);

  const { getRootProps, getInputProps, isDragActive } = useDropzone({ onDrop });

  const handleFileChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const files = event.target.files;
    if (files && files.length > 0) {
      setFile(files[0]);
      onFileChange(files[0]);
    }
  };

  return (
    <div className="mb-4">
      <label className="block mb-2">{label}</label>
      <div
        {...getRootProps()}
        className={`w-full p-6 border-2 border-dashed rounded-lg cursor-pointer ${
          isDragActive ? "border-blue-500" : "border-gray-200 dark:border-gray-600"
        }`}
      >
        <input {...getInputProps()} onChange={handleFileChange} />
        {file ? (
          <div className="flex items-center justify-center space-x-4">
            <img src={URL.createObjectURL(file)} alt="Preview" className="w-16 h-16 object-cover rounded-full" />
            <span className="text-gray-700 dark:text-gray-300">{file.name}</span>
          </div>
        ) : (
          <div className="flex flex-col items-center justify-center space-y-2">
            <CloudArrowUpIcon className="w-10 h-10 text-gray-400" />
            <p className="text-gray-600 dark:text-gray-400">Drag & drop a file here, or click to select a file</p>
          </div>
        )}
      </div>
    </div>
  );
};

export default ImageUpload;