#version 300 es

in vec3 inPosition;
in vec3 inColor;
in vec2 inTexPosition;

out vec3 vertexColor;
out vec2 vertexTexPosition;

void main() {
    gl_Position = vec4(inPosition, 1);
    vertexColor = inColor;
    vertexTexPosition = inTexPosition;
}