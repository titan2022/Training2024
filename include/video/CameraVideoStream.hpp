#ifndef CAMERA_VIDEO_STREAM
#define CAMERA_VIDEO_STREAM

#include "util/ConfigReader.hpp"
#include "video/VideoStream.hpp"
#include <memory>
#include <opencv2/core.hpp>
#include <opencv2/videoio.hpp>

class CameraVideoStream : public VideoStream
{
  public:
	int id = -1;
	std::shared_ptr<ConfigReader> config;
	int initStream();
	cv::Mat getNextFrame();
    bool isOpened();
    int getWidth();
    int getHeight();
    int getFPS();

  private:
	cv::VideoCapture cap;
};

#endif