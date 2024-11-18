# Build calibration script
add_executable(calibration ${CMAKE_SOURCE_DIR}/src/calibration.cpp ${SRC})
target_include_directories(calibration PUBLIC ${OpenCV_INCLUDE_DIRS} PRIVATE ${CMAKE_SOURCE_DIR}/include)
target_link_libraries(calibration ${OpenCV_LIBS} nlohmann_json::nlohmann_json)
set_target_properties(calibration PROPERTIES RUNTIME_OUTPUT_DIRECTORY ${CMAKE_RUNTIME_OUTPUT_DIRECTORY})

# Build square detector
add_executable(squareDetect ${CMAKE_SOURCE_DIR}/src/squareDetect.cpp ${SRC})
target_include_directories(squareDetect PUBLIC ${OpenCV_INCLUDE_DIRS} PRIVATE ${CMAKE_SOURCE_DIR}/include)
target_link_libraries(squareDetect ${OpenCV_LIBS} nlohmann_json::nlohmann_json)
set_target_properties(squareDetect PROPERTIES RUNTIME_OUTPUT_DIRECTORY ${CMAKE_RUNTIME_OUTPUT_DIRECTORY})