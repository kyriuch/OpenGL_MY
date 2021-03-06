#version 300 es

in vec3 inPosition;
in vec3 inColor;

out vec3 vertexColor;

void main() {
    gl_Position = vec4(inPosition, 1);
    vertexColor = inColor;
}