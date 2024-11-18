#include <filesystem>
#include <fstream>
#include <iostream>
#include <string>

#include <nlohmann/json.hpp>
#include <opencv2/core.hpp>

#include "util/ConfigReader.hpp"

namespace fs = std::filesystem;
using json = nlohmann::json;

int ConfigReader::readFromFile(std::string path)
{
	this->configPath = path;

	fs::path filePathObj((fs::path(path)));
	if (!fs::exists(filePathObj))
	{
		return 2;
	}
	std::ifstream in(filePathObj);
	if (!in)
	{
		return 5;
	}
	json data = json::parse(in);

	for (auto &tagObj : data["cameras"])
	{
		camera.width = (int)tagObj["width"];
		camera.height = (int)tagObj["height"];
		camera.fps = (int)tagObj["fps"];
		camera.exposure = (int)tagObj["exposure"];
		camera.focalX = (double)tagObj["focalX"];
		camera.focalY = (double)tagObj["focalY"];
		camera.centerX = (double)tagObj["centerX"];
		camera.centerY = (double)tagObj["centerY"];

		camera.cameraMat = cv::Mat(3, 3, CV_64FC1, cv::Scalar::all(0));
		camera.cameraMat.at<double>(0, 0) = camera.focalX;
		camera.cameraMat.at<double>(1, 1) = camera.focalY;
		camera.cameraMat.at<double>(0, 2) = camera.centerX;
		camera.cameraMat.at<double>(1, 2) = camera.centerY;
		camera.cameraMat.at<double>(2, 2) = 1;

		camera.distCoeffs = cv::Mat(5, 1, CV_64FC1, cv::Scalar::all(0));
		camera.distCoeffs.at<double>(0, 0) = (double)tagObj["k1"];
		camera.distCoeffs.at<double>(0, 1) = (double)tagObj["k2"];
		camera.distCoeffs.at<double>(0, 2) = (double)tagObj["p1"];
		camera.distCoeffs.at<double>(0, 3) = (double)tagObj["p2"];
		camera.distCoeffs.at<double>(0, 4) = (double)tagObj["k3"];
	}

	return 0;
}