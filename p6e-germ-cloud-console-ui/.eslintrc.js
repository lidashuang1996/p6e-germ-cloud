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
    "space-before-function-paren": 0,
    // 'space-before-function-paren': ['error', 'always'],
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
    HttpManageJurisdictionPathGroupListItemDataResult: true,


    HttpMessageGroupListParam: true,
    HttpMessageGroupListItemDataResult: true,
    HttpMessageGroupListDataResult: true,
    HttpMessageGroupListResult: true,

    HttpMessagePlatformListParam: true,
    HttpMessagePlatformListItemDataResult: true,
    HttpMessagePlatformListDataResult: true,
    HttpMessagePlatformListResult: true,

    HttpMessageTemplateListParam: true,
    HttpMessageTemplateListItemDataResult: true,
    HttpMessageTemplateListDataResult: true,
    HttpMessageTemplateListResult: true,


    HttpMessageLogListParam: true,
    HttpMessageLogListItemDataResult: true,
    HttpMessageLogListDataResult: true,
    HttpMessageLogListResult: true,

    HttpMessagePlatformDataResult: true,
    HttpMessageTemplateDataResult: true,
    HttpMessageTemplateCreateParam: true,
    HttpMessageTemplateUpdateParam: true,
    HttpMessagePlatformCreateParam: true,
    HttpMessagePlatformUpdateParam: true,
    HttpMessagePlatformGroupCreateParam: true,
    HttpMessagePlatformGroupDeleteParam: true,
    HttpMessagePlatformGroupUpdateParam: true,
    HttpManageDictionaryListParam: true,
    HttpManageDictionaryListItemDataResult: true,
    HttpManageDictionaryCreateParam: true,
    HttpManageDictionaryUpdateParam: true,
    HttpManageJurisdictionPathListParam: true,
    HttpManageJurisdictionUrlCreateParam: true,
    HttpManageJurisdictionUrlUpdateParam: true,
    HttpManageJurisdictionUrlGroupListParam: true,
    HttpManageJurisdictionUrlGroupListItemDataResult: true


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
