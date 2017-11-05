#version 300 es
precision mediump float;

in vec3 vertexColor;
in vec2 vertexTexPosition;

uniform sampler2D uniTexture;

out vec4 fragmentColor;

void main() {
    fragmentColor = vec4(vertexColor, 1f) * texture(uniTexture, vertexTexPosition);
}