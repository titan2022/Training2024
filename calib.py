import cv2
import math

SQUARE_LENGTH = 0.0254 * 1.5
MARKER_LENGTH = SQUARE_LENGTH * 0.6

aruco_dict = cv2.aruco.getPredefinedDictionary(cv2.aruco.DICT_6X6_1000)
board = cv2.aruco.CharucoBoard((5, 7), SQUARE_LENGTH, MARKER_LENGTH, aruco_dict)
arucoParams = cv2.aruco.DetectorParameters()

counter, corners_list, id_list = [], [], []

cap = cv2.VideoCapture(0)
# cap.set(cv2.CAP_PROP_FRAME_WIDTH, 640)
# cap.set(cv2.CAP_PROP_FRAME_HEIGHT, 480)

image_size = None

image_count = 0

while cap.isOpened():
    status, image = cap.read()
    key = cv2.waitKey(1)

    if not status:
        continue

    gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
    image_size = gray.shape
    corners, ids, rejected = cv2.aruco.detectMarkers(
        image,
        aruco_dict,
        parameters=arucoParams
    )

    if len(corners) == 0:
        cv2.imshow("Camera Calibration", image)
        if key == 27: # ESC key
            break
        continue

    resp, charuco_corners, charuco_ids = cv2.aruco.interpolateCornersCharuco(
        markerCorners=corners,
        markerIds=ids,
        image=gray,
        board=board
    )

    if not resp:
        continue

    cv2.aruco.drawDetectedCornersCharuco(image, charuco_corners, charuco_ids, (255, 0, 0))

    if key == 32: # Space key
        if resp > 20:
            corners_list.append(charuco_corners)
            id_list.append(charuco_ids)
            image_count += 1
            print("Image added: " + str(image_count))
    elif key == 27: # ESC key
        break
  
    cv2.imshow("Camera Calibration", image)

ret, matrix, distortion, rvecs, tvecs = cv2.aruco.calibrateCameraCharuco(
    charucoCorners=corners_list, 
    charucoIds=id_list,
    board=board, 
    imageSize=image_size, 
    cameraMatrix=None, 
    distCoeffs=None
)


focal_x = matrix[0][0]
focal_y = matrix[1][1]
center_x = matrix[0][2]
center_y = matrix[1][2]

k_1 = distortion[0][0]
k_2 = distortion[0][1]
p_1 = distortion[0][2]
p_2 = distortion[0][3]
k_3 = distortion[0][4]

fov_x = 2.0 * math.atan(cap.get(cv2.CAP_PROP_FRAME_WIDTH) / (2.0 * focal_x))
fov_y = 2.0 * math.atan(cap.get(cv2.CAP_PROP_FRAME_HEIGHT) / (2.0 * focal_y))

print("fov_x: " + str(fov_x * (180.0 / math.pi)))
print("fov_y: " + str(fov_y * (180.0 / math.pi)))
