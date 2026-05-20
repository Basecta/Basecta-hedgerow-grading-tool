package com.basecta.upload.exception;

public final class UploadExceptionMessages {
    public static final String TOKEN_NOT_FOUND = "This upload link does not exist.";
    public static final String TOKEN_EXPIRED = "This upload link has expired. Please request a new one.";
    public static final String QUOTA_EXCEEDED = "This upload link has reached its maximum number of uploads.";
    public static final String FILE_EMPTY = "No file was provided in the request.";
    public static final String UPLOAD_FAILED = "Upload failed due to a server error. Please try again.";
    public static final String UNSUPPORTED_FILE_TYPE = "Unsupported file type. Accepted: JPEG, PNG, TIFF, WebP, HEIC.";

    private UploadExceptionMessages() {}  // prevent instantiation
}
