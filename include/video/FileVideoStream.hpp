#ifndef FILE_VIDEO_STREAM
#define FILE_VIDEO_STREAM

#include "util/ConfigReader.hpp"
#include "video/VideoStream.hpp"
#include <memory>
#include <opencv2/core.hpp>
#include <opencv2/videoio.hpp>

/**
 * @brief Reads video from specified file.
 * Requires `filePath` and `config` to be specified before initialization.
 */
class FileVideoStream : public VideoStream
{
  public:
	std::string filePath = "";
	std::shared_ptr<ConfigReader> config;
	int initStream();
	cv::Mat getNextFrame();

  private:
	cv::VideoCapture cap;
};

#endif