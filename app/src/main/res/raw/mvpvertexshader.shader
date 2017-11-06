#version 300 es

in vec3 inPosition;
in vec3 inColor;

uniform mat4 uMVPMatrix;

out vec3 vertexColor;

void main() {
    gl_Position = uMVPMatrix * vec4(inPosition, 1);
    vertexColor = inColor;
}