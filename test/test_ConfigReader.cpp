#include <filesystem>
#include <gtest/gtest.h>
#include <string>
#include "util/ConfigReader.hpp"

namespace fs = std::filesystem;

// Horrible test, don't do this...
TEST(ConfigTest, IO)
{
	fs::path configFolderPath = fs::current_path().parent_path() / "test" / "data" / "test_config.json";
	ConfigReader config;
	int error = config.readFromFile(configFolderPath.string());

	ASSERT_NE(error, 2) << "IO Error 2: Config file not found inside " << configFolderPath.string();
	ASSERT_EQ(error, 0) << "IO Error " << std::to_string(error) << ": Unknown error with loading config file.";

    // Integer parsing
    ASSERT_EQ(config.camera.width, 640) << "IO Error 5: Could not parse `int: width` correctly.";
    ASSERT_EQ(config.camera.height, 480) << "IO Error 5: Could not parse `int: width` correctly.";
    ASSERT_EQ(config.camera.fps, 30) << "IO Error 5: Could not parse `int: fps` correctly.";
    ASSERT_EQ(config.camera.exposure, 0) << "IO Error 5: Could not parse `int: exposure` correctly.";

    // Double parsing
    ASSERT_DOUBLE_EQ(config.camera.focalX, 1.0) << "IO Error 5: Could not parse `double: focalX` correctly.";
    // Too lazy to check the rest of the values lol
}