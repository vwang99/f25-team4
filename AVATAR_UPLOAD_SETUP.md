# Avatar/Profile Picture Upload Feature

This document explains how to set up and use the avatar upload feature using Cloudinary.

## Setup Instructions

### 1. Get Cloudinary Credentials

1. Go to https://cloudinary.com/ and sign up for a free account
2. Navigate to https://cloudinary.com/console
3. Copy your:
   - **Cloud Name** (visible at the top)
   - **API Key** (visible under Account)
   - **API Secret** (visible under Account)

### 2. Configure application.properties

Update `src/main/resources/application.properties` with your Cloudinary credentials:

```properties
cloudinary.cloud-name=your-cloud-name
cloudinary.api-key=your-api-key
cloudinary.api-secret=your-api-secret
```

**IMPORTANT:** Do NOT commit your real API credentials to GitHub. Use environment variables instead:
```properties
cloudinary.cloud-name=${CLOUDINARY_CLOUD_NAME:demo}
cloudinary.api-key=${CLOUDINARY_API_KEY:your-api-key}
cloudinary.api-secret=${CLOUDINARY_API_SECRET:your-api-secret}
```

Then set environment variables before running:
- Windows PowerShell:
  ```powershell
  $env:CLOUDINARY_CLOUD_NAME = "your-cloud-name"
  $env:CLOUDINARY_API_KEY = "your-api-key"
  $env:CLOUDINARY_API_SECRET = "your-api-secret"
  ```
- Linux/Mac:
  ```bash
  export CLOUDINARY_CLOUD_NAME="your-cloud-name"
  export CLOUDINARY_API_KEY="your-api-key"
  export CLOUDINARY_API_SECRET="your-api-secret"
  ```

### 3. Rebuild the Project

```bash
mvn clean install
```

## API Endpoint

### Upload Avatar for a User

**Endpoint:** `POST /users/{id}/upload-avatar`

**Request Type:** `multipart/form-data`

**Parameters:**
- `id` (path): User ID (required)
- `file` (form data): Image file to upload (required, must be an image)

**Response:**
```json
{
  "imageUrl": "https://res.cloudinary.com/your-cloud/image/upload/v1234567890/tether/avatars/xyz.jpg"
}
```

## Testing the Endpoint

```
### Postman/Thunderclient
1. Set request method to **POST**
2. URL: `http://localhost:8080/users/1/upload-avatar`
3. Go to **Body** tab → Select **form-data**
4. Add a key named `file` with type **File**
5. Select your image file
6. Click **Send**

## How It Works

1. **Client sends image** via POST to `/users/{id}/upload-avatar`
2. **ImageUploadService** validates the file:
   - Ensures file is not empty
   - Checks that it's an image file
3. **Cloudinary API** receives the file and:
   - Stores it in the cloud
   - Optimizes the image (compression, resizing options available)
   - Returns a secure HTTPS URL
4. **AppUser entity** is updated with the image URL
5. **Response** contains the Cloudinary URL which can be used in the frontend

## File Validation

The endpoint validates:
- File is not empty
- Content-Type starts with `image/` (jpeg, png, gif, webp, etc.)

If validation fails, you'll get a 400 Bad Request with an error message.

## Troubleshooting

### "File must be an image"
- Make sure you're uploading an actual image file (jpg, png, gif, webp, etc.)
- Check Content-Type header

### "Upload failed: Invalid credentials"
- Verify your Cloudinary API credentials in application.properties
- Check environment variables are set correctly

### "Connection refused"
- Make sure the Spring Boot application is running on `http://localhost:8080`

### Image URL doesn't load
- Verify your Cloud Name is correct
- Check Cloudinary dashboard for upload details
- Ensure the image folder (tether/avatars) was created on Cloudinary

## Next Steps

To use the uploaded avatar URL on the frontend:
1. Fetch the user profile: `GET /users/{id}`
2. The response will include `profileImageUrl` with the Cloudinary URL
3. Display the image in your HTML: `<img src="{{ user.profileImageUrl }}" />`

## Security Notes

- API credentials should never be committed to Git
- Use environment variables for all sensitive configuration
- Only image files are accepted
- File size limits can be configured in Cloudinary dashboard
- Consider adding file size validation on the backend
