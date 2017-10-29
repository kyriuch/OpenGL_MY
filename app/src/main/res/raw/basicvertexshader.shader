#version 300 es

in vec3 position;

out vec4 vColor;

void main() {
    gl_Position = vec4(position, 0);
    vColor = vec4(0.5f, 0.25f, 0.75f, 1f);
}