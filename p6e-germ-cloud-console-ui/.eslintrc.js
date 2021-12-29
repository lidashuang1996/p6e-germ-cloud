module.exports = {
  root: true,
  env: {
    node: true
  },
  extends: [
    'plugin:vue/vue3-essential',
    '@vue/standard',
    '@vue/typescript/recommended'
  ],
  parserOptions: {
    ecmaVersion: 2020
  },
  rules: {
    'valid-v-slot': 'off',
    'no-console': process.env.NODE_ENV === 'production' ? 'warn' : 'off',
    'no-debugger': process.env.NODE_ENV === 'production' ? 'warn' : 'off',
    semi: ['error', 'always'],
    'space-before-function-paren': ['error', 'always'],
    'no-empty-function': ['warn', { allow: ['functions'] }]
  },
  globals: {
    // @/types/store.d.ts
    TableView: true,
    HttpManageUserListParam: true,
    HttpManageUserListItemDataResult: true,
    HttpManageJurisdictionPathListParam: true,
    HttpManageJurisdictionPathListItemDataResult: true,
    HttpManageJurisdictionPathGroupListParam: true,
    HttpManageJurisdictionPathGroupListItemDataResult: true
  },
  overrides: [
    {
      files: [
        '**/__tests__/*.{j,t}s?(x)',
        '**/tests/unit/**/*.spec.{j,t}s?(x)'
      ],
      env: {
        jest: true
      }
    }
  ]
}
