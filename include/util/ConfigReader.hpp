#ifndef CONFIG_READER
#define CONFIG_READER

#include <string>
#include "Camera.hpp"

/**
 * @brief Interface for config.json, contains camera and tag information. See docs for config structure
 *
 */
class ConfigReader
{
  public:
	// Stuff from config.json
	Camera camera;

	/**
	 * @brief Reads config.json from specified path
	 *
	 * @param path Path for config file
	 * @return int Error code
	 */
	int readFromFile(std::string path);

  private:
	std::string configPath;
};

#endif