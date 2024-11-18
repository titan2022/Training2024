# General config
cmake_minimum_required(VERSION 3.20.0) # CMake 3.20.0
set(CMAKE_CXX_STANDARD 20) # C++20
project(Training2024 VERSION 1.0.0 LANGUAGES C CXX)

# Custom CLI flag options
option(TEST "Build all tests." OFF)

# Standard directories
set(CMAKE_RUNTIME_OUTPUT_DIRECTORY ${CMAKE_SOURCE_DIR}/bin)
set(CMAKE_MODULE_PATH ${PROJECT_SOURCE_DIR}/cmake ${CMAKE_MODULE_PATH})

# OpenCV linking
set(OpenCV_DIR /usr/lib/opencv)
option(OpenCV_STATIC OFF)
find_package(OpenCV 4.8)

# nlohmann/json linking
find_package(nlohmann_json CONFIG REQUIRED)

file(GLOB SRC 
    src/util/*.cpp
    src/video/*.cpp
)
# include_directories(src)

include(${CMAKE_MODULE_PATH}/build_execs.cmake)

if (TEST)
    include(${CMAKE_MODULE_PATH}/build_tests.cmake)
endif()